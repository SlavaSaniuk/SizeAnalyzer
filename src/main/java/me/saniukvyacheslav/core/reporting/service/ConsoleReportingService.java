package me.saniukvyacheslav.core.reporting.service;

import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.report.Report;

import java.util.Objects;

@NoArgsConstructor
public class ConsoleReportingService implements ReportingService {

    private final StringBuilder reportsStrings = new StringBuilder();
    private Report currentReport;
    @Override
    public void setReport(Report aReport) {
        Objects.requireNonNull(aReport, "Report instance must be not null.");
        this.currentReport = aReport;
    }

    @Override
    public void formReport(Object... formingArgs) {
        if (this.currentReport == null)
            throw new IllegalStateException("Report instance must be initialized before.");

        this.currentReport.getRecords().forEach(record -> {
            switch (record.getRecordType().getType()) {
                case 0: {
                    this.reportsStrings.append(record.getRecordText()).append("\n");
                    break;
                }
                case 1: {
                    this.reportsStrings.append(record.getFile()).append(":").append("\n");
                    break;
                }
                case 2: {
                    this.reportsStrings.append(record.getFile()).append(" - ").append(record.getFileSize()).append("\n");
                }
            }
        });
    }

    @Override
    public void showReport() {
        System.out.println(this.reportsStrings);
    }
}

