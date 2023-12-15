package me.saniukvyacheslav.core.reporting;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.service.ConsoleReportingService;
import me.saniukvyacheslav.core.reporting.service.ReportingService;
import me.saniukvyacheslav.core.reporting.service.configuration.ReportingServiceConfiguration;

/**
 * Configuration for all reporting system.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportingConfiguration {

    private static ReportingConfiguration INSTANCE; // Singleton instance;
    private ReportingService consoleReportingService;

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
     * Construct new ReportingService default configuration.
     * @return - configuration.
     */
    public ReportingServiceConfiguration defaultConfiguration() {
        return ReportingServiceConfiguration.builder().build();
    }
}
