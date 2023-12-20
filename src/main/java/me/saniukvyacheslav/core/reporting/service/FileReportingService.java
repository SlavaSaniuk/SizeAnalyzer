package me.saniukvyacheslav.core.reporting.service;

import me.saniukvyacheslav.core.reporting.report.Report;

import java.io.File;

public class FileReportingService extends BaseReportingService {

    private File outputFile;

    @Override
    public void formReport(Report aReport) {
        super.formReport(aReport);

    }

    @Override
    public void showReport() {

    }
}
