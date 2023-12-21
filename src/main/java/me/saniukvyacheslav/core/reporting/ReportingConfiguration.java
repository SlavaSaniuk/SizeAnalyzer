package me.saniukvyacheslav.core.reporting;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.service.ConsoleReportingService;
import me.saniukvyacheslav.core.reporting.service.CsvReportingService;
import me.saniukvyacheslav.core.reporting.service.FileReportingService;
import me.saniukvyacheslav.core.reporting.service.ReportingService;
import me.saniukvyacheslav.core.reporting.service.configuration.ReportingServiceConfiguration;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Configuration for the reporting system.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportingConfiguration {

    private static ReportingConfiguration INSTANCE; // Singleton instance;
    private ReportingService consoleReportingService;
    private ReportingService fileReportingService; // FileReportingService instance.
    private ReportingService csvReportingService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm.ss");

    /**
     * Get current application wide reporting configuration.
     * @return - reporting configuration instance.
     */
    public static ReportingConfiguration getInstance() {
        if (INSTANCE == null) INSTANCE = new ReportingConfiguration();
        return INSTANCE;
    }

    /**
     * Get current console reporting service.
     * @return - console reporting service.
     */
    public ReportingService getConsoleReportingService() {
        if (this.consoleReportingService != null) return this.consoleReportingService;
        else this.consoleReportingService = new ConsoleReportingService();

        return this.consoleReportingService;
    }

    /**
     * Get current file reporting service implementation.
     * Output file name will be "report-[CURRENT_DATE_TIME].txt";
     * @return - file reporting service.
     */
    public ReportingService getFileReportingService() {
        if (this.fileReportingService != null) return this.fileReportingService;

        FileReportingService fileReportingService = new FileReportingService();
        String outputName = String.format("report-%s.txt", LocalDateTime.now().format(this.dateTimeFormatter));
        fileReportingService.setOutputFile(new File(outputName));
        this.fileReportingService = fileReportingService;
        return fileReportingService;
    }

    public ReportingService getCsvReportingService() {
        if (this.csvReportingService != null) return this.csvReportingService;

        CsvReportingService csvReportingService = new CsvReportingService();
        String outputName = String.format("report-%s.csv", LocalDateTime.now().format(this.dateTimeFormatter));
        csvReportingService.setOutputFile(new File(outputName));
        this.csvReportingService = csvReportingService;
        return csvReportingService;
    }

    /**
     * Construct new ReportingService default configuration.
     * @return - configuration.
     */
    public ReportingServiceConfiguration defaultConfiguration() {
        return ReportingServiceConfiguration.builder().build();
    }
}
