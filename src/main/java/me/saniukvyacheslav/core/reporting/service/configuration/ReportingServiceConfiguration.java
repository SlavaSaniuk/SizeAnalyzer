package me.saniukvyacheslav.core.reporting.service.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * This configuration class allow to extend {@link me.saniukvyacheslav.core.reporting.service.ReportingService} functionality.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportingServiceConfiguration {

    @Getter private MeasureUnit usedMeasureUnitType;
    @Getter private int digitNumInDecPart;
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

        /**
         * Set measure units type.
         * @param aUnit - units type.
         * @return - this builder.
         */
        public ReportingServiceConfigurationBuilder measureUnits(MeasureUnit aUnit) {
            Objects.requireNonNull(aUnit, "Measure unit type must be not null.");
            this.measureUnit = aUnit;
            return this;
        }

        public ReportingServiceConfigurationBuilder digitNumberInDecimalPart(int aNum) {
            if (aNum<0)
                throw new IllegalArgumentException("Digits count in decimal part must be equal or greater zero.");
            this.digitNumDecPart = aNum;
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
           configuration.usedBuilder = this;
           return configuration;
        }

    }

}
