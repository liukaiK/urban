package com.king.urban.core.controller.event.report;

import com.king.urban.common.Result;
import com.king.urban.core.pojo.dto.event.report.GridAdminReportDTO;
import com.king.urban.core.pojo.dto.event.report.TelephoneReportDTO;
import com.king.urban.core.service.event.report.ReportService;
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

    /**
     * 热线上报
     */
    @PostMapping("/telephone")
    public Result telephone(@RequestBody TelephoneReportDTO reportDTO) {
        telephoneReportService.report(reportDTO);
        return Result.success();
    }

    /**
     * 网格员上报
     */
    @PostMapping("/gridadmin")
    public Result gridAdmin(@RequestBody GridAdminReportDTO reportDTO) {
        gridAdminReportService.report(reportDTO);
        return Result.success();
    }


}
