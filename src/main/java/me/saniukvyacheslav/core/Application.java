package me.saniukvyacheslav.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.cli.CommandLineArguments;
import me.saniukvyacheslav.core.exceptions.InvalidImportantCommandLineArgumentsException;
import me.saniukvyacheslav.core.exceptions.NoSuchImportantArgumentException;
import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.dao.ReportDao;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.space.SpaceAnalysisConfiguration;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {

    private static Application INSTANCE;
    @Getter private final Map<String, String> applicationArguments = new HashMap<>();
    private final Pattern targetPathPattern = Pattern.compile("/t=", Pattern.CASE_INSENSITIVE);

    /**
     * Get the singleton instance of this class.
     * @return - application.
     */
    public static Application getInstance() {
        if (INSTANCE == null) INSTANCE = new Application();
        return INSTANCE;
    }

    /**
     * Parse arguments array.
     * @param args - arguments array.
     * @throws InvalidImportantCommandLineArgumentsException - Throws in cases, when important argument value is invalid.
     * @throws NoSuchImportantArgumentException - Throws in cases, when at least one important argument value is absent.
     */
    public void parseArguments(String[] args) throws InvalidImportantCommandLineArgumentsException, NoSuchImportantArgumentException {

        // Iterate throw argument's array:
        for (String arg : args) {
            if (this.isTargetPathArguments(arg)) {
                this.setApplicationArgument(CommandLineArguments.TARGET.getFullName(), this.getTargetPathArgumentValue(arg));
                break;
            }
        }

        // Check important arguments:
        if (!this.applicationArguments.containsKey(CommandLineArguments.TARGET.getFullName()))
            throw new NoSuchImportantArgumentException(CommandLineArguments.TARGET.getFullName(), "/" +CommandLineArguments.TARGET.getCutName() +"=");

    }

    /**
     * Set application argument.
     * @param anArgumentFullName - argument name.
     * @param anArgumentValue - argument value.
     */
    public void setApplicationArgument(String anArgumentFullName, String anArgumentValue) {
        this.applicationArguments.put(anArgumentFullName, anArgumentValue);
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
     * @throws InvalidImportantCommandLineArgumentsException - Throws in cases, when a target path is an invalid
     * file path, or file/directory by path is not exist.
     */
    public String getTargetPathArgumentValue(String anArgument) throws InvalidImportantCommandLineArgumentsException {
        Objects.requireNonNull(anArgument, "Argument [anArguments] must be not null.");

        String filePath = anArgument.substring(3);
        try {
            if (filePath.isEmpty()) throw new InvalidImportantCommandLineArgumentsException("Target path must be not empty.");
            Paths.get(filePath);
            if (!new File(filePath).exists())
                throw new InvalidImportantCommandLineArgumentsException(String.format("There is no file or directory by path [%s].", filePath));
        }catch (InvalidPathException e) {
            throw new InvalidImportantCommandLineArgumentsException(String.format("Target path [%s] is invalid.", filePath));
        }

        return filePath;
    }

    /**
     * Launch application.
     * If this method returns not zero value, in means than method ends with exceptions.
     * @param args - application arguments.
     * @return - exit code.
     */
    public static int launch(String[] args) {

        try {
            Application.getInstance().parseArguments(args);
        } catch (InvalidImportantCommandLineArgumentsException | NoSuchImportantArgumentException e) {
            System.out.println(e.getMessage());
            return 1;
        }
        return Application.getInstance().run();
    }

    /**
     * Run application after parsing arguments.
     * If this method returns not zero value, in means than method ends with exceptions.
     * @return - exit code.
     */
    private int run() {

        // Create report:
        Report report = ReportDao.getReport("report-1");

        // Analyze space:
        String targetPath = this.applicationArguments.get(CommandLineArguments.TARGET.getFullName());
        SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService().setReport(report);
        SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService().analyse(new File(targetPath), true);

        ReportingConfiguration.getInstance().getConsoleReportingService().formReport(report);
        ReportingConfiguration.getInstance().getConsoleReportingService().show();

        return 0;
    }
}
