package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.reporting.service.configuration.ConfigurableReportingService;
import me.saniukvyacheslav.core.reporting.service.out.ReportingOut;

/**
 * Common service to form, show reports of analyzing.
 */
public interface ReportingService extends ConfigurableReportingService, ReportingOut {

    /**
     * Generate report (make hierarchy, insert title, etc.).
     * @param aReport - source report.
     */
    void formReport(Report aReport);

    /**
     * Clear report format and text (Not from inner Report object).
     */
    void clear();

}
