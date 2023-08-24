package com.king.urban.event.controller.report;

import com.king.urban.event.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @PostMapping("/telephone")
//    public Result telephone(@RequestBody TelephoneReport reportDTO) {
//        telephoneReportService.report(reportDTO);
//        return Result.success();
//    }
//
//    /**
//     * 网格员上报
//     */
//    @PostMapping("/gridadmin")
//    public Result gridAdmin(@RequestBody GridAdminReport reportDTO) {
//        gridAdminReportService.report(reportDTO);
//        return Result.success();
//    }


}
