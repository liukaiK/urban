package com.king.urban.event.service.report;

import com.king.urban.event.entity.event.Source;
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
    protected Source getSource() {
        return Source.TELEPHONE;
    }

}
