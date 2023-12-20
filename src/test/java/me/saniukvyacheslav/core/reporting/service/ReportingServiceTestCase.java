package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.dao.ReportDao;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.space.SpaceAnalysisConfiguration;
import me.saniukvyacheslav.core.space.SpaceAnalysisService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ReportingServiceTestCase {

    private static final SpaceAnalysisService spaceAnalysisService = SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService();
    private static final Report testReport = ReportDao.getReport("test-report-1");
    private ReportingService testService;


    @BeforeAll
    static void beforeAll() {
        spaceAnalysisService.setReport(testReport);
        spaceAnalysisService.analyse(new File("E:\\001_WORK\\"), true);
    }

    @BeforeEach
    void beforeEach() {
        this.testService = ReportingConfiguration.getInstance().getConsoleReportingService();
        this.testService.clear();
    }

    @Test
    void consoleReportingService_showReport_shouldPrintReportToConsole() {
        this.testService.formReport(testReport);
        this.testService.show();
    }

}
