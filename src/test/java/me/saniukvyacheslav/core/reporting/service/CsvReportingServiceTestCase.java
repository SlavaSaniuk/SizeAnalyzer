package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.dao.ReportDao;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.reporting.service.configuration.MeasureUnit;
import me.saniukvyacheslav.core.reporting.service.configuration.ReportingServiceConfiguration;
import me.saniukvyacheslav.core.space.SpaceAnalysisConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class CsvReportingServiceTestCase {

    private ReportingService reportingService;
    private static final Report TEST_REPORT = ReportDao.getReport("test-report-2");
    private static final ReportingServiceConfiguration testConfiguration = ReportingServiceConfiguration.builder()
            .measureUnits(MeasureUnit.MEGABYTE)
            .digitNumberInDecimalPart(2)
            .pathSizeSeparatorString(",")
            .build();

    @BeforeAll
    static void beforeAll() {
        SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService().setReport(TEST_REPORT);
        SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService().analyse(new File("E:\\001_WORK\\"), true);
    }

    @BeforeEach
    void beforeEach() {
        this.reportingService = ReportingConfiguration.getInstance().getCsvReportingService();
        this.reportingService.configure(testConfiguration);
        this.reportingService.clear();
    }

    @Test
    void show_testReport2_shouldCreateCsvFile() {
        this.reportingService.formReport(TEST_REPORT);
        this.reportingService.out();
        File generatedFile = ((FileReportingService) this.reportingService).getOutputFile();
        Assertions.assertNotNull(generatedFile);
        Assertions.assertTrue(generatedFile.exists());
        this.reportingService.show();
    }

}
