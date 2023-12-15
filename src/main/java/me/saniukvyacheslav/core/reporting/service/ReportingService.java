package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.report.Report;

/**
 * Common service to form, show reports of analyzing.
 */
public interface ReportingService {

    /**
     * Set report to this service.
     * @param aReport - report to set.
     */
    void setReport(Report aReport);

    /**
     * Form report (make hierarchy, insert title, etc.).
     * @param formingArgs - forming arguments.
     */
    void formReport(Object... formingArgs);

    /**
     * Show report.
     */
    void showReport();

}
