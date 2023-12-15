package me.saniukvyacheslav.core.reporting.dao;

import me.saniukvyacheslav.core.reporting.report.Report;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * DAO for {@link Report} instances.
 */
public class ReportDao {

    private static final Map<String, Report> reports = new HashMap<>();

    /**
     * Get/create report by name.
     * @param aReportName - report name.
     * @return - report instance.
     */
    public static Report getReport(String aReportName) {
        Objects.requireNonNull(aReportName, "Report name must be not null.");

        if (reports.containsKey(aReportName))
            return reports.get(aReportName);
        else reports.put(aReportName, new Report());
        return reports.get(aReportName);
    }
}
