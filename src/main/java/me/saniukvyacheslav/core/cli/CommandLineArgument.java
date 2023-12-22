package me.saniukvyacheslav.core.cli;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class CommandLineArgument {

    @Getter private boolean isImportant;
    @Getter private String name;
    @Getter @Setter private String value;


    public CommandLineArgument(boolean isImportant, String aName) {
        Objects.requireNonNull(aName, "Argument name must be not null.");
        this.isImportant = isImportant;
        this.name = aName;
    }

    public CommandLineArgument(boolean isImportant, String aName, String aValue) {
        this(isImportant, aName);
        this.value = aValue;
    }

}
