package me.saniukvyacheslav.core.reporting.report;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportRecord {

    @Getter @Setter private String recordText;
    @Getter @Setter private String file;
    @Getter @Setter private String fileSize;
    @Getter @Setter private RecordType recordType;

    public static ReportRecord emptyRecord() {
        ReportRecord record = new ReportRecord();
        record.recordType = RecordType.TEXT;
        record.recordText = "";
        return record;
    }

    public static ReportRecord textRecord(String aText) {
        ReportRecord record = new ReportRecord();
        record.recordType = RecordType.TEXT;
        record.recordText = aText;
        return record;
    }

    public static ReportRecord hierarchyRecord(File aFile) {
        ReportRecord record = new ReportRecord();
        record.recordType = RecordType.HIERARCHY;
        record.file = aFile.getAbsolutePath();
        return record;
    }

    public static ReportRecord sizeRecord(File aFile, long aSize) {
        ReportRecord record = new ReportRecord();
        record.recordType = RecordType.SIZE;
        record.file = aFile.getAbsolutePath();
        record.fileSize = String.valueOf(aSize);
        return record;
    }
}
