package com.king.urban.main.event;

import com.king.urban.main.event.service.Report;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultReportHandlerManager implements ReportHandlerManager, InitializingBean {

    private List<ReportHandler> reportHandlers;

    @Override
    public void report(Report report) {
        for (ReportHandler handler : reportHandlers) {

            if (!handler.supports(report.getClass())) {
                continue;
            }

            handler.report(report);
            break;

        }
    }


    @Override
    public void afterPropertiesSet() {
//        Assert.notEmpty(this.reportHandlers, "reportHandlers cannot be null");
    }

}
