package me.saniukvyacheslav.core.cli;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CommandLineArguments {

    TARGET("t", "target"),
    REPORT_TYPE("rt", "reportType"),
    MEASURE_UNIT("mu", "measureUnit"),
    SEPARATOR_STRING("ss", "separatorString"),
    HELP("h", "help");


    @Getter private final String cutName;
    @Getter private final String fullName;

}
