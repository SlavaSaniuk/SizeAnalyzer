package me.saniukvyacheslav.core.reporting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileReportingServiceTests {

    @Test
    void out_fileIsNotSet_shouldThrowISE() {
        Assertions.assertThrows(IllegalStateException.class, () -> new FileReportingService().out());
    }

    @Test
    void out_fileIsNotExist_shouldCreateIt() {
        FileReportingService reportingService = new FileReportingService();
        File outputFile = new File("C:\\Test.file");

        reportingService.setOutputFile(outputFile);
        reportingService.out();

        Assertions.assertTrue(outputFile.exists());
    }

    @Test
    void show_fileIsNotSet_shouldThrowISE() {
        Assertions.assertThrows(IllegalStateException.class, () -> new FileReportingService().show());
    }

    @Test
    void show_fileIsNotExist_shouldThrowISE() {
        FileReportingService service = new FileReportingService();
        service.setOutputFile(new File("C:\\Test.file"));
        Assertions.assertThrows(IllegalStateException.class, () -> new FileReportingService().show());
    }

    @Test
    void show_reportWasOutedBefore_shouldOpenFile() {
        FileReportingService reportingService = new FileReportingService();
        File outputFile = new File("C:\\Test.txt");

        reportingService.setOutputFile(outputFile);
        reportingService.out();

        Assertions.assertTrue(outputFile.exists());

        reportingService.show();
    }
}
