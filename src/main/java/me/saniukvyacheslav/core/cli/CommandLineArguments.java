package me.saniukvyacheslav.core.cli;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum of supported command line arguments.
 */
@AllArgsConstructor
public enum CommandLineArguments {

    /**
     * Analysis target.
     */
    TARGET("t", "Target"),
    /**
     * Out report type.
     */
    REPORT_TYPE("rt", "Report type"),
    /**
     * Measure unit of space.
     */
    MEASURE_UNIT("mu", "Measure unit"),
    SEPARATOR_STRING("ss", "Separator string"),
    HELP("h", "Help");

    @Getter private final String cutName;
    @Getter private final String fullName;

}
