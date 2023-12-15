package me.saniukvyacheslav.core.reporting.service.configuration;

/**
 * {@link me.saniukvyacheslav.core.reporting.service.ReportingService} implementations
 * can be configured via {@link ReportingServiceConfiguration} instance.
 */
public interface ConfigurableReportingService {

    /**
     * Configure reporting service.
     * @param aConfiguration - configuration instance.
     */
    void configure(ReportingServiceConfiguration aConfiguration);

    /**
     * Reset service configuration to default value.
     */
    void resetConfiguration();
}
