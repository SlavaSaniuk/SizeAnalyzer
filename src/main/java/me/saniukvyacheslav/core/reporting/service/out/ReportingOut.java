package me.saniukvyacheslav.core.reporting.service.out;

/**
 * ReportingOut functionality allows out/show generated report to different destinations (File, console).
 */
public interface ReportingOut {

    /**
     * Output a generated report to somewhere.
     */
    void out();

    /**
     * Show a generated report to user.
     */
    void show();

}
