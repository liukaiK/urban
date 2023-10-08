package com.king.urban.event;

import com.king.urban.event.service.Report;

public interface ReportHandler {

    void report(Report report);

    boolean supports(Class<?> report);

}
