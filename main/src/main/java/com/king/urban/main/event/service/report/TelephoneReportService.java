package com.king.urban.main.event.service.report;

import com.king.urban.main.event.entity.event.Source;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 热线上报
 *
 * @author liukai
 */
@Service
@Transactional
public class TelephoneReportService extends AbstractReportTemplate implements ReportService {

    @Override
    public boolean isStartWorkflow() {
        return true;
    }

    @Override
    protected Source getSource() {
        return Source.TELEPHONE;
    }

}
