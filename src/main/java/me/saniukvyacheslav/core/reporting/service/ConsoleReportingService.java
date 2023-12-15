package me.saniukvyacheslav.core.reporting.service;

import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.reporting.report.ReportRecord;
import me.saniukvyacheslav.core.reporting.service.configuration.ReportingServiceConfiguration;

import java.util.Objects;

@NoArgsConstructor
public class ConsoleReportingService implements ReportingService {

    private final StringBuilder reportsStrings = new StringBuilder();
    private ReportingServiceConfiguration currentConfiguration = ReportingConfiguration.getInstance().defaultConfiguration(); // This service configuration.

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
    public void showReport() {
        System.out.println(this.reportsStrings);
    }

    @Override
    public void clear() {
        this.reportsStrings.setLength(0);
    }

    @Override
    public void configure(ReportingServiceConfiguration aConfiguration) {
        Objects.requireNonNull(aConfiguration, "Reporting service configuration must be not null.");
        this.currentConfiguration = aConfiguration;
    }

    @Override
    public void resetConfiguration() {
        this.currentConfiguration = ReportingConfiguration.getInstance().defaultConfiguration();
    }

    private void appendFileSizeRecord(ReportRecord fileSizeRecord) {
        if (fileSizeRecord == null) return;

        // Convert bytes to used measure units:
        long srcFileSize = Long.parseLong(fileSizeRecord.getFileSize());
        double fileSize = (double) srcFileSize/this.currentConfiguration.getUsedMeasureUnitType().getSizeInBytes();
        this.reportsStrings.append(fileSizeRecord.getFile()).append(" - ")
                .append(fileSize).append(" ").append(this.currentConfiguration.getUsedMeasureUnitType().getShortName())
                .append("\n");
    }
}

