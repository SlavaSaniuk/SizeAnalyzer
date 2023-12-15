package me.saniukvyacheslav.core.reporting.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * All report records types.
 */
@AllArgsConstructor
public enum RecordType {

    /**
     * Simple text record type.
     */
    TEXT(0),
    /**
     * Hierarchy record type.
     */
    HIERARCHY(1),
    /**
     * File/Directors size record type.
     */
    SIZE(2);

    @Getter private final int type; // Record type;
}
