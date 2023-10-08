package com.king.urban.event.controller.report;

import com.king.urban.common.Result;
import com.king.urban.event.ReportHandlerManager;
import com.king.urban.event.service.GridAdminReport;
import com.king.urban.event.service.report.ReportService;
import com.king.urban.event.service.report.TelephoneReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 上报案件
 *
 * @author liukai
 */
@RestController
@RequestMapping("/event/report")
public class ReportController {

    @Autowired
    private ReportService telephoneReportService;

    @Autowired
    private ReportService gridAdminReportService;

    @Autowired
    private ReportHandlerManager reportHandlerManager;

    /**
     * 热线上报
     */
    @PostMapping("/telephone")
    public Result telephone(@RequestBody TelephoneReport reportDTO) {
        reportHandlerManager.report(reportDTO);
        return Result.success();
    }

    /**
     * 网格员上报
     */
    @PostMapping("/gridadmin")
    public Result gridAdmin(@RequestBody GridAdminReport reportDTO) {
        reportHandlerManager.report(reportDTO);
        return Result.success();
    }


}
