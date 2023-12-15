package me.saniukvyacheslav.core.space;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SpaceAnalysisServiceImplTests {

    private static final Logger LOGGER = LoggingConfiguration.getLogger(SpaceAnalysisServiceImplTests.class);

    @Test
    void fileSize_fileIsExist_shouldReturnFileSizeInBytes() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();

        String testFilePath = "C:\\pagefile.sys";
        long testResult = spaceAnalysisService.fileSize(new File(testFilePath));

        Assertions.assertNotEquals(0, testResult);
        LOGGER.debugf("Size of %s file: %d bytes;", testFilePath, testResult);
    }

    @Test
    void fileSize_fileIsNull_shouldThrowNPE() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();
        Assertions.assertThrows(NullPointerException.class, () -> spaceAnalysisService.fileSize(null));
    }

    @Test
    void fileSize_fileNotExist_shouldThrowIAE() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> spaceAnalysisService.fileSize(new File("C:\\123.file")));
    }

    @Test
    void fileSize_fileNotRegularFile_shouldThrowIAE() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> spaceAnalysisService.fileSize(new File("C:\\Windows")));
    }

    @Test
    void directorySize_fileIsNotDirectory_shouldReturnFileSize() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();

        String testFilePath = "C:\\pagefile.sys";
        long testResult = spaceAnalysisService.directorySize(new File(testFilePath));

        Assertions.assertNotEquals(0, testResult);
        LOGGER.debugf("Size of %s file: %d bytes;", testFilePath, testResult);
    }

    @Test
    void directorySize_simpleDirectory_shouldReturnDirectorySize() {
        SpaceAnalysisServiceImpl spaceAnalysisService = new SpaceAnalysisServiceImpl();

        String testFilePath = "C:\\Windows";
        long testResult = spaceAnalysisService.directorySize(new File(testFilePath));

        Assertions.assertNotEquals(0, testResult);
        LOGGER.debugf("Size of %s directory: %d bytes;", testFilePath, testResult);
    }

}
