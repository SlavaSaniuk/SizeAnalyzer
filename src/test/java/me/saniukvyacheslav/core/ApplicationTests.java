package me.saniukvyacheslav.core;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.exceptions.InvalidImportantCommandLineArgumentsException;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTests {

    private static final Application INSTANCE = Application.getInstance();
    private static final Logger LOGGER = LoggingConfiguration.getLogger(ApplicationTests.class);

    @Test
    void isTargetPathArgument_invalidArgName_shouldReturnFalseNull() {
        String arg="/ndsm=asdsa";
        Assertions.assertFalse(INSTANCE.isTargetPathArguments(arg));
    }

    @Test
    void isTargetPathArgument_validArg_shouldReturnTrue() {
        String arg="/t=";
        Assertions.assertTrue(INSTANCE.isTargetPathArguments(arg));
    }

    @Test
    void getTargetPathArgumentValue_invalidFilePath_shouldThrowIICMAE() {
        String arg="/t=";
        Exception e = Assertions.assertThrows(InvalidImportantCommandLineArgumentsException.class, ()-> INSTANCE.getTargetPathArgumentValue(arg));
        LOGGER.debugf("Argument [%s], exception message: [%s];", arg, e.getMessage());

        String arg1="/t=asdasdasd";
        Exception e1 = Assertions.assertThrows(InvalidImportantCommandLineArgumentsException.class, ()-> INSTANCE.getTargetPathArgumentValue(arg1));
        LOGGER.debugf("Argument [%s], exception message: [%s];", arg1, e1.getMessage());
    }

    @Test
    void getTargetPathArgumentValue_validFilePath_shouldReturnFilePath() {
        String arg="/t=E:\\\\001_WORK";
        try {
            String filePath = INSTANCE.getTargetPathArgumentValue(arg);
            Assertions.assertNotNull(filePath);
            LOGGER.debugf("Argument [%s], file path: [%s];", arg, filePath);
        } catch (InvalidImportantCommandLineArgumentsException e) {
            Assertions.fail();
        }
    }

}
