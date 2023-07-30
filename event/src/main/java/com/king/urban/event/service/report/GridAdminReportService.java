package com.king.urban.event.service.report;

import com.king.urban.event.entity.event.Source;
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


    /**
     * 网格员上报是要进入草稿箱中 先不开启工作流
     */
    @Override
    public boolean isStartWorkflow() {
        return false;
    }

    @Override
    protected Source getSource() {
        return Source.GRID_ADMIN;
    }

}
