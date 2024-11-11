package com.king.urban.event;

import com.king.urban.event.service.Report;

public class TelportPhoneReportHandler implements ReportHandler {


    @Override
    public void report(Report report) {

    }

    @Override
    public boolean supports(Class<?> report) {
        return (TelportPhoneReportHandler.class.isAssignableFrom(report));
    }


}
