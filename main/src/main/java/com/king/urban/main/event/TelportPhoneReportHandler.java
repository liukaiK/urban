package com.king.urban.main.event;

import com.king.urban.main.event.service.Report;

public class TelportPhoneReportHandler implements ReportHandler {


    @Override
    public void report(Report report) {

    }

    @Override
    public boolean supports(Class<?> report) {
        return (TelportPhoneReportHandler.class.isAssignableFrom(report));
    }


}
