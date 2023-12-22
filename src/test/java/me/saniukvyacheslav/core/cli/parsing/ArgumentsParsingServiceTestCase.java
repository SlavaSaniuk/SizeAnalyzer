package me.saniukvyacheslav.core.cli.parsing;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.core.logging.LoggingConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ArgumentsParsingServiceTestCase {

    private final ArgumentsParsingService argumentsParsingService = new ArgumentsParser();
    private static final Logger LOGGER = LoggingConfiguration.getLogger(ArgumentsParsingServiceTestCase.class);

    @Test
    void parse_noArguments_shouldReturnEmptyMap() {
        Assertions.assertEquals(0, this.argumentsParsingService.parse(new String[]{}).size());
    }

    @Test
    void parse_fewArguments_shouldReturnMap() {
        String[] args = {"/t=E:\\\\001_WORK", "/mu=kb", "/rt=CON"};
        Map<String, String> arguments = this.argumentsParsingService.parse(args);
        Assertions.assertEquals(args.length, arguments.size());
        arguments.forEach((key, value) -> LOGGER.debugf("Key: [%s], value [%s];", key, value));
    }

}
