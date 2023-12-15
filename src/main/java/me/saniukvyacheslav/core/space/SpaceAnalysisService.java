package me.saniukvyacheslav.core.space;

import me.saniukvyacheslav.core.reporting.report.Report;

import java.io.File;

public interface SpaceAnalysisService {

    /**
     * Analyze used space by file or directory.
     * @param aFile - file or directory.
     * @param isRecursively - is recursive.
     * @return - file or directory size.
     */
    long analyse(File aFile, boolean isRecursively);

    /**
     * Set project report to this service.
     * @param aReport - report to set.
     */
    void setReport(Report aReport);


}
