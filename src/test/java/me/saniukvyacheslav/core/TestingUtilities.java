package me.saniukvyacheslav.core;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.dao.ReportDao;
import me.saniukvyacheslav.core.reporting.report.Report;
import me.saniukvyacheslav.core.space.SpaceAnalysisConfiguration;
import me.saniukvyacheslav.core.space.SpaceAnalysisService;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestingUtilities {

    private static TestingUtilities INSTANCE; // Singleton instance;
    private final SpaceAnalysisService spaceAnalysisService = SpaceAnalysisConfiguration.getInstance().getSpaceAnalysisService();
    @Getter private final Report testReport = ReportDao.getReport("test-report-1");
    @Getter private final String testDirectory = "E:\\001_WORK\\";
    private boolean isInitialized;

    public static TestingUtilities getInstance() {
        if (INSTANCE == null) INSTANCE = new TestingUtilities();
        return INSTANCE;
    }

    public void init(boolean isRecursively) {
        this.spaceAnalysisService.setReport(this.testReport);
        this.spaceAnalysisService.analyse(new File(this.testDirectory), isRecursively);
        this.isInitialized = true;
    }

    public SpaceAnalysisService getSpaceAnalysisServiceIfInitialized() {
        if (!this.isInitialized)
            throw new IllegalStateException("SpaceAnalysisService must be initialized before.");
        return this.spaceAnalysisService;
    }



}
