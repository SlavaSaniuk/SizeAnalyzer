package me.saniukvyacheslav.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.cli.CommandLineArguments;
import me.saniukvyacheslav.core.reporting.ReportingConfiguration;
import me.saniukvyacheslav.core.reporting.dao.ReportDao;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.space.SpaceAnalysisConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Application {

    private static Application INSTANCE;
    @Getter private final Map<String, String> applicationArguments = new HashMap<>(); // Argument name / argument value pairs;

    /**
     * Get the singleton instance of this class.
     * @return - application.
     */
    public static Application getInstance() {
        if (INSTANCE == null) INSTANCE = new Application();
        return INSTANCE;
    }

    /**
     * Launch application.
     * If this method returns not zero value, in means than method ends with exceptions.
     * @param args - application arguments.
     * @return - exit code.
     */
    public static int launch(String[] args) {
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
