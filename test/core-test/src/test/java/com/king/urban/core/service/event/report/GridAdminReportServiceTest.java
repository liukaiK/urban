package com.king.urban.core.service.event.report;

import com.king.urban.core.BaseTest;
import com.king.urban.core.pojo.dto.event.report.GridAdminReportDTO;
import com.king.urban.core.pojo.dto.event.report.ReportDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class GridAdminReportServiceTest extends BaseTest {

    @Autowired
    private ReportService gridAdminReportService;


    @Test
    @Transactional
    @Rollback(value = false)
    public void gridAdminReportTest() {
        ReportDTO reportDTO = new GridAdminReportDTO();
        gridAdminReportService.report(reportDTO);
    }


}
