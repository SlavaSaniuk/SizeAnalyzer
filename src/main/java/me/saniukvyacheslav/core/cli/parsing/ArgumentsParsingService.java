package me.saniukvyacheslav.core.cli.parsing;

import java.util.Map;

/**
 * Command line arguments parsing service.
 */
public interface ArgumentsParsingService {

    /**
     * Parse command line arguments.
     * @param args - arguments array.
     * @return - key/value map.
     */
    Map<String, String> parse(String[] args);

}
