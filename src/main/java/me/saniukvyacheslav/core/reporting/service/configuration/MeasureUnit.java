package me.saniukvyacheslav.core.reporting.service.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Space measure units.
 */
@AllArgsConstructor
public enum MeasureUnit {

    BYTE(1, "b"),
    KILOBYTE(1024, "kb"),
    MEGABYTE(1048576, "mb"),
    GIGABYTE(1073741824, "gb");

    @Getter private final long sizeInBytes;
    @Getter private final String shortName;

}
