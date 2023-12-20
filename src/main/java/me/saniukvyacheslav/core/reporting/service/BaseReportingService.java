package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.reporting.report.ReportRecord;
import me.saniukvyacheslav.core.reporting.service.configuration.ReportingServiceConfiguration;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public abstract class BaseReportingService implements ReportingService {

    protected final StringBuilder reportsStrings = new StringBuilder(); // String from report;
    private final DecimalFormat decimalFormat; // For format double values;
    private ReportingServiceConfiguration currentConfiguration; // This service configuration.

    public BaseReportingService() {
        this.decimalFormat = new DecimalFormat();
        this.decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        this.currentConfiguration = ReportingConfiguration.getInstance().defaultConfiguration();
        this.changeDecimalFormat(this.currentConfiguration.getDigitNumInDecPart());
    }

    @Override
    public void formReport(Report aReport) {
        Objects.requireNonNull(aReport, "Report instance must be not null.");

        aReport.getRecords().forEach(record -> {
            switch (record.getRecordType().getType()) {
                case 0: {
                    this.reportsStrings.append(record.getRecordText()).append("\n");
                    break;
                }
                case 1: {
                    this.reportsStrings.append(record.getFile()).append(":").append("\n");
                    break;
                }
                case 2: { // FileSize record:
                    this.appendFileSizeRecord(record);
                }
            }
        });
    }

    @Override
    public void clear() {
        this.reportsStrings.setLength(0);
    }

    @Override
    public void configure(ReportingServiceConfiguration aConfiguration) {
        Objects.requireNonNull(aConfiguration, "Reporting service configuration must be not null.");
        this.currentConfiguration = aConfiguration;

        // Change decimal format;
        this.changeDecimalFormat(this.currentConfiguration.getDigitNumInDecPart());
    }

    @Override
    public void resetConfiguration() {
        this.currentConfiguration = ReportingConfiguration.getInstance().defaultConfiguration();
    }

    private void changeDecimalFormat(int aDigitsNumInDecPart) {
        if (aDigitsNumInDecPart<0)
            throw new IllegalArgumentException("Digits number in decimal part must be equal or greater zero.");

        StringBuilder decimalFormat = new StringBuilder("0");
        if (aDigitsNumInDecPart>0) {
            decimalFormat.append(".");
            for(int i=0; i<aDigitsNumInDecPart; i++) decimalFormat.append("0");
        }

        this.decimalFormat.applyPattern(decimalFormat.toString());
    }

    private void appendFileSizeRecord(ReportRecord fileSizeRecord) {
        if (fileSizeRecord == null) return;

        // Convert bytes to used measure units:
        long srcFileSize = Long.parseLong(fileSizeRecord.getFileSize());

        double fileSize = (double) srcFileSize/this.currentConfiguration.getUsedMeasureUnitType().getSizeInBytes();

        this.reportsStrings.append(fileSizeRecord.getFile()).append(" - ")
                .append(this.formatDouble(fileSize)).append(" ").append(this.currentConfiguration.getUsedMeasureUnitType().getShortName())
                .append("\n");
    }

    private String formatDouble(double aDbl) {
        return this.decimalFormat.format(aDbl);
    }

}
