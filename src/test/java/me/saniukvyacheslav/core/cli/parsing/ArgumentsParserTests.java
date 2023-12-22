package me.saniukvyacheslav.core.cli.parsing;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentsParserTests {

    private static final Logger LOGGER = LoggingConfiguration.getLogger(ArgumentsParserTests.class);

    @Test
    void isTargetPathArgument_invalidArgName_shouldReturnFalse() {
        String arg="/ndsm=asdsa";
        Assertions.assertFalse(new ArgumentsParser().isTargetPathArguments(arg));
    }

    @Test
    void isTargetPathArgument_validArg_shouldReturnTrue() {
        String arg="/t=";
        Assertions.assertTrue(new ArgumentsParser().isTargetPathArguments(arg));
    }

    @Test
    void getTargetPathArgumentValue_validFilePath_shouldReturnFilePath() {
        String arg="/t=E:\\\\001_WORK";
        String filePath = new ArgumentsParser().getTargetPathArgumentValue(arg);
        Assertions.assertNotNull(filePath);
        LOGGER.debugf("Argument [%s], file path: [%s];", arg, filePath);
    }

    @Test
    void isMeasureUnitArgument_invalidArgName_shouldReturnFalse() {
        String arg="/ndsm=asdsa";
        Assertions.assertFalse(new ArgumentsParser().isMeasureUnitArgument(arg));
    }

    @Test
    void isMeasureUnitArgument_validArg_shouldReturnTrue() {
        String arg="/mu=";
        Assertions.assertTrue(new ArgumentsParser().isMeasureUnitArgument(arg));
    }

    @Test
    void getMeasureUnitArgument_str_shouldReturnValue() {
        String arg="/mu=";
        String value = new ArgumentsParser().getMeasureUnitArgument(arg);
        Assertions.assertEquals("", value);
        LOGGER.debugf("Argument [%s], value: %s;", arg, value);

        String arg1="/mu=mb";
        value = new ArgumentsParser().getMeasureUnitArgument(arg1);
        Assertions.assertEquals("mb", new ArgumentsParser().getMeasureUnitArgument(arg1));
        LOGGER.debugf("Argument [%s], value: %s;", arg1, value);
    }

    @Test
    void isReportTypeArgument_validArg_shouldReturnTrue() {
        String arg="/rt=";
        Assertions.assertTrue(new ArgumentsParser().isReportTypeArgument(arg));
    }

    @Test
    void getRepotyTypeArgument_str_shouldReturnValue() {
        String arg="/rt=";
        String value = new ArgumentsParser().getReportTypeArgument(arg);
        Assertions.assertEquals("", value);
        LOGGER.debugf("Argument [%s], value: %s;", arg, value);

        String arg1="/rt=CSV";
        value = new ArgumentsParser().getReportTypeArgument(arg1);
        Assertions.assertEquals("CSV", new ArgumentsParser().getMeasureUnitArgument(arg1));
        LOGGER.debugf("Argument [%s], value: %s;", arg1, value);
    }

    @Test
    void isArgument_notArgument_shouldReturnFalse() {
        String arg1 = "=";
        Assertions.assertFalse(new ArgumentsParser().isArgument(arg1));

        String arg2 = "/=asdas";
        Assertions.assertFalse(new ArgumentsParser().isArgument(arg2));

        String arg3 = "asdasd=asdasd";
        Assertions.assertFalse(new ArgumentsParser().isArgument(arg3));

        String arg5 = "asdasdasASDASDASd";
        Assertions.assertFalse(new ArgumentsParser().isArgument(arg5));
    }

    @Test
    void isArgument_argument_shouldReturnTrue() {
        String arg1 = "/a=";
        Assertions.assertTrue(new ArgumentsParser().isArgument(arg1));

        String arg2 = "/a=b";
        Assertions.assertTrue(new ArgumentsParser().isArgument(arg2));
    }

    @Test
    void getArgumentKey_validArgument_shouldReturnKey() {
        String arg = "/hello=world";
        Assertions.assertEquals("hello", new ArgumentsParser().getArgumentKey(arg));
    }

    @Test
    void getArgumentValue_validArgument_shouldReturnValue() {
        String arg = "/hello=world";
        Assertions.assertEquals("world", new ArgumentsParser().getArgumentValue(arg));
    }

    @Test
    void parse_argsIsNull_shouldThrowNPE() {
        Assertions.assertThrows(NullPointerException.class, ()-> new ArgumentsParser().parse(null));
    }

    @Test
    void parse_noArguments_shouldReturnEmptyMap() {
        Assertions.assertEquals(0, new ArgumentsParser().parse(new String[]{}).size());
    }

}
