package me.saniukvyacheslav.core.reporting.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * All supported report types.
 */
@AllArgsConstructor
public enum ReportType {

    /**
     * Console report.
     */
    CONSOLE("CON"),
    /**
     * File report.
     */
    FILE("FILE"),
    /**
     * CSV report.
     */
    CSV("CSV");

    @Getter private final String reportType;
}
