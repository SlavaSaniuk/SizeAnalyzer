package me.saniukvyacheslav.core.reporting.service;

import lombok.NoArgsConstructor;

/**
 * This implementation of {@link ReportingService} service used to output generated reports string on console.
 * This service used in command line environment.
 */
@NoArgsConstructor
public class ConsoleReportingService extends BaseReportingService {

    /**
     * Output generated reports strings to console.
     */
    @Override
    public void out() {
        System.out.println(super.reportStrings.toString());
    }

    /**
     * Show generated reports strings in console.
     */
    @Override
    public void show() {
        System.out.println(super.reportStrings.toString());
    }
}

