package com.king.urban.main.event;

import com.king.urban.main.event.service.Report;

public interface ReportHandler {

    void report(Report report);

    boolean supports(Class<?> report);

}
