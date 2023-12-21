package me.saniukvyacheslav.core.reporting.service.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * This configuration class allows
 * to extend {@link me.saniukvyacheslav.core.reporting.service.ReportingService} functionality.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportingServiceConfiguration {

    @Getter private MeasureUnit usedMeasureUnitType;
    @Getter private int digitNumInDecPart;
    @Getter private String pathSizeSeparator;
    @Getter private boolean isIncludeHierarchyRecords;
    @Getter private boolean isIncludeTextRecords;
    @Getter private ReportingServiceConfigurationBuilder usedBuilder;

    /**
     * Create builder for this class.
     * @return - builder for this class.
     */
    public static ReportingServiceConfigurationBuilder builder() {
        return new ReportingServiceConfigurationBuilder();
    }

    /**
     * Builder for {@link ReportingServiceConfiguration} configuration.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ReportingServiceConfigurationBuilder {
        private MeasureUnit measureUnit = MeasureUnit.BYTE;
        private int digitNumDecPart=2;
        private String pathSizeSeparatorString = " - ";
        private boolean builderIsIncludeHR = true;
        private boolean builderIsIncludeTR = true;

        /**
         * Set measure units type.
         * By default, bytes.
         * @param aUnit - units type.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder measureUnits(MeasureUnit aUnit) {
            Objects.requireNonNull(aUnit, "Measure unit type must be not null.");
            this.measureUnit = aUnit;
            return this;
        }

        /**
         * Set count of digits in decimal part in files size.
         * By default, 2.
         * @param aNum - count of digits.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder digitNumberInDecimalPart(int aNum) {
            if (aNum<0)
                throw new IllegalArgumentException("Digits count in decimal part must be equal or greater zero.");
            this.digitNumDecPart = aNum;
            return this;
        }

        /**
         * Set separator string between file path and file size.
         * By default, " - ".
         * @param aSeparator - separator characters.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder pathSizeSeparatorString(String aSeparator) {
            Objects.requireNonNull(aSeparator, "Separator string must be not null.");
            this.pathSizeSeparatorString = aSeparator;
            return this;
        }

        /**
         * Is an includes hierarchy records in a report.
         * By default, true.
         * @param isInclude - true/false.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder includeHierarchyRecords(boolean isInclude) {
            this.builderIsIncludeHR = isInclude;
            return this;
        }

        /**
         * Is an includes text records in a report.
         * By default, true.
         * @param isInclude - true/false.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder includeTextRecords(boolean isInclude) {
            this.builderIsIncludeTR = isInclude;
            return this;
        }

        /**
         * Build new configuration instance.
         * @return - new instance.
         */
        public ReportingServiceConfiguration build() {
           ReportingServiceConfiguration configuration = new ReportingServiceConfiguration();
           configuration.usedMeasureUnitType = this.measureUnit;
           configuration.digitNumInDecPart = this.digitNumDecPart;
           configuration.pathSizeSeparator = this.pathSizeSeparatorString;
           configuration.isIncludeHierarchyRecords = this.builderIsIncludeHR;
           configuration.isIncludeTextRecords = this.builderIsIncludeTR;
           configuration.usedBuilder = this;
           return configuration;
        }

    }

}
