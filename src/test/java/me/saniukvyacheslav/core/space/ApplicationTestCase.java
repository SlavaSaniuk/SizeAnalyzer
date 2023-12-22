package me.saniukvyacheslav.core.space;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.Application;
import me.saniukvyacheslav.core.exceptions.InvalidImportantCommandLineArgumentsException;
import me.saniukvyacheslav.core.exceptions.NoSuchImportantArgumentException;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApplicationTestCase {

    private static final Logger LOGGER = LoggingConfiguration.getLogger(ApplicationTestCase.class);

    @Test
    void parseArguments_invalidImportantArgument_shouldThrowIICLAE() {
        String[] args = {"/t=asdasdas"};
        Assertions.assertThrows(InvalidImportantCommandLineArgumentsException.class, () -> Application.getInstance().parseArguments(args));
    }

    @Test
    void parseArguments_noTargetPathArgument_shouldThrowNSIAE() {
        String[] args = {""};
        Exception e = Assertions.assertThrows(NoSuchImportantArgumentException.class, () -> Application.getInstance().parseArguments(args));
        LOGGER.error(e.getMessage());
    }

    @Test
    void launch_invalidImportantArgument_shouldReturn1() {
        String[] args = {"/t=dsfsdfsd"};
        Assertions.assertEquals(1, Application.launch(args));
    }

    @Test
    void launch_validImportantArgument_shouldShowReport() {
        String[] args = {"/t=E:\\\\001_WORK"};
        Assertions.assertEquals(0, Application.launch(args));
    }
}
