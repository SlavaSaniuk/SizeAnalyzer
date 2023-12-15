package me.saniukvyacheslav.core.space;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class SpaceAnalysisServiceTestCase {

    private static final SpaceAnalysisService spaceAnalysisService = new SpaceAnalysisServiceImpl();
    private static final Logger LOGGER = LoggingConfiguration.getLogger(SpaceAnalysisServiceTestCase.class);

    @Test
    void analyse_simpleDirectory_shouldReturnDirectorySize() {
        String testFile = "D:\\English";
        long actualSize = spaceAnalysisService.analyse(new File(testFile), false);

        Assertions.assertNotEquals(0, actualSize);
        LOGGER.debugf("Size of [%s] is %d bytes;", testFile, actualSize);
    }

}
