package com.king.urban.event.service.report;

import com.king.urban.event.entity.Source;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 网格员上报
 *
 * @author liukai
 */
@Service
@Transactional
public class GridAdminReportService extends AbstractReportTemplate implements ReportService {


    @Override
    protected Source getSource() {
        return Source.GRID_ADMIN;
    }

}
