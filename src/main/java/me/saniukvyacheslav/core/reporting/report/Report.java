package me.saniukvyacheslav.core.reporting.report;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple report POJO.
 */
@NoArgsConstructor
public class Report {

    @Getter private final List<ReportRecord> records = new ArrayList<>(); // List of report records.

    /**
     * Insert file size record to this report.
     * @param aFile - file.
     * @param aFileSize - file size.
     */
    public void insertSizeRecord(File aFile, long aFileSize) {
        this.records.add(ReportRecord.sizeRecord(aFile, aFileSize));
    }

    /**
     * Insert hierarchy record to this report.
     * @param aDirectory - hierarchy level.
     */
    public void insertHierarchyRecord(File aDirectory) {
        this.records.add(ReportRecord.hierarchyRecord(aDirectory));
    }

    /**
     * Insert simple text record.
     * @param aText - text.
     */
    public void insertTextRecord(String aText) {
        this.records.add(ReportRecord.textRecord(aText));
    }

}
