package me.saniukvyacheslav.core.cli.parsing;

import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.cli.CommandLineArguments;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link ArgumentsParsingService} service.
 */
@NoArgsConstructor
public class ArgumentsParser implements ArgumentsParsingService {

    private final Pattern targetPathPattern = Pattern.compile("/t=", Pattern.CASE_INSENSITIVE);
    private final Pattern measureUnitPattern = Pattern.compile("/mu=", Pattern.CASE_INSENSITIVE);
    private final Pattern reportTypePattern = Pattern.compile("/rt=", Pattern.CASE_INSENSITIVE);
    private final Pattern unrecognizedArgumentPatter = Pattern.compile("/[a-zA-z0-9]+=[a-zA-z0-9]*");

    /**
     * Parse command line arguments.
     * @param args - arguments array.
     * @return - key/value map.
     */
    @Override
    public Map<String, String> parse(String[] args) {
        Objects.requireNonNull(args, "Arguments array must be not null.");
        Map<String, String> arguments = new HashMap<>();

        for (String argument: args) {
            if (this.isTargetPathArguments(argument)) {
                arguments.put(CommandLineArguments.TARGET.getFullName(), this.getTargetPathArgumentValue(argument));
            }else if (this.isMeasureUnitArgument(argument)) {
                arguments.put(CommandLineArguments.MEASURE_UNIT.getFullName(), this.getMeasureUnitArgument(argument));
            }else if (this.isReportTypeArgument(argument)) {
                arguments.put(CommandLineArguments.REPORT_TYPE.getFullName(), this.getReportTypeArgument(argument));
            }else if (this.isArgument(argument)) {
                arguments.put(this.getArgumentKey(argument), this.getArgumentValue(argument));
            }else arguments.put(argument, "");
        }

        return arguments;
    }

    /**
     * Check whether a specified argument string is a target analysis path.
     * @param anArgument - argument.
     * @return - true/false.
     */
    public boolean isTargetPathArguments(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument string must be not null.");
        return (this.targetPathPattern.matcher(anArgument).find());
    }

    /**
     * Extract target path value from argument.
     * @param anArgument - cl argument.
     * @return - target path.
     */
    public String getTargetPathArgumentValue(String anArgument){
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");
        return anArgument.substring(3);
    }

    /**
     * Check whether a specified argument is measure unit configuration parameter.
     * @param anArgument - argument.
     * @return - true/false.
     */
    public boolean isMeasureUnitArgument(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");
        return (this.measureUnitPattern.matcher(anArgument).find());
    }

    /**
     * Get measure unit argument value.
     * @param anArgument - argument.
     * @return - argument value.
     */
    public String getMeasureUnitArgument(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");
        return anArgument.substring(4);
    }

    /**
     * Check whether a specified argument is report type value.
     * @param anArgument - argument.
     * @return - true/false.
     */
    public boolean isReportTypeArgument(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");
        return (this.reportTypePattern.matcher(anArgument).find());
    }

    /**
     * Get report type argument value.
     * @param anArgument - argument.
     * @return - argument value.
     */
    public String getReportTypeArgument(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");
        return anArgument.substring(4);
    }

    /**
     * Check whether specified string is argument.
     * @param anArgument - string.
     * @return - true/false.
     */
    public boolean isArgument(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument string must be not null.");
        return this.unrecognizedArgumentPatter.matcher(anArgument).find();
    }

    /**
     * Get argument key.
     * @param anArgument - argument string.
     * @return - key.
     */
    public String getArgumentKey(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument string must be not null.");
        int i = anArgument.indexOf("=");
        return anArgument.substring(0, i).substring(1);
    }

    /**
     * Get argument value.
     * @param anArgument - argument string.
     * @return - value.
     */
    public String getArgumentValue(String anArgument) {
        Objects.requireNonNull(anArgument, "Argument string must be not null.");
        int i = anArgument.indexOf("=");
        return anArgument.substring(i).substring(1);
    }
}
