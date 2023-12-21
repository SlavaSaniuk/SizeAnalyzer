package me.saniukvyacheslav.core.reporting.service;

public class CsvReportingService extends FileReportingService {

    @Override
    protected boolean isIncludeHR() {
        return false;
    }

    @Override
    protected boolean isIncludeTR() {
        return false;
    }
}
