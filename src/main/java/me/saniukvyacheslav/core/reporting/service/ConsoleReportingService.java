package me.saniukvyacheslav.core.reporting.service;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConsoleReportingService extends BaseReportingService {

    @Override
    public void showReport() {
        System.out.println(super.reportsStrings);
    }

}

