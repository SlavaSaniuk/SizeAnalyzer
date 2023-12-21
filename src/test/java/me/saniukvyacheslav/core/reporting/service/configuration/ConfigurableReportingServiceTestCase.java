package me.saniukvyacheslav.core.reporting.service.configuration;

import me.saniukvyacheslav.core.TestingUtilities;
import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.service.ReportingService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConfigurableReportingServiceTestCase {

    private ReportingService testService;

    @BeforeAll
    static void beforeAll() {
        TestingUtilities.getInstance().init(true);
    }

    @BeforeEach
    void beforeEach() {
        this.testService = ReportingConfiguration.getInstance().getConsoleReportingService();
    }

    @AfterEach
    void afterEach() {
        this.testService.clear();
        this.testService.resetConfiguration();
    }

    @Test
    void configure_defaultConfiguration_shouldPrintSizeInBytes() {
        this.testService.formReport(TestingUtilities.getInstance().getTestReport());
        this.testService.out();
    }

    @Test
    void configure_Kb_shouldPrintSizeInKb() {
        this.testService.configure(ReportingServiceConfiguration.builder().measureUnits(MeasureUnit.KILOBYTE).build());
        this.testService.formReport(TestingUtilities.getInstance().getTestReport());
        this.testService.out();
    }

    @Test
    void configure_mb_shouldPrintSizeInMegaBytes() {
        this.testService.configure(ReportingServiceConfiguration.builder().measureUnits(MeasureUnit.MEGABYTE).build());
        this.testService.formReport(TestingUtilities.getInstance().getTestReport());
        this.testService.out();
    }

    @Test
    void configure_gb_shouldPrintSizeInGb() {
        this.testService.configure(ReportingServiceConfiguration.builder().measureUnits(MeasureUnit.GIGABYTE).build());
        this.testService.formReport(TestingUtilities.getInstance().getTestReport());
        this.testService.out();
    }

    @Test
    void configure_separatorStrIsSemicolon_shouldPrintFileSizeRecordsWithSemicolon() {
        this.testService.configure(ReportingServiceConfiguration.builder()
                .measureUnits(MeasureUnit.MEGABYTE)
                .pathSizeSeparatorString(";")
                .build());
        this.testService.formReport(TestingUtilities.getInstance().getTestReport());
        this.testService.out();
    }

}
