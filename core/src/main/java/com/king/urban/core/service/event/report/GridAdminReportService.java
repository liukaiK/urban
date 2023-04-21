package com.king.urban.core.service.event.report;

import com.king.urban.core.entity.event.Event;
import com.king.urban.core.entity.event.Source;
import com.king.urban.core.pojo.dto.event.report.ReportDTO;
import com.king.urban.core.repository.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 网格员上报
 *
 * @author liukai
 */
@Service
@Transactional
public class GridAdminReportService implements ReportService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void report(ReportDTO reportDTO) {
        Event event = new Event(Source.GRID_ADMIN);
        eventRepository.save(event);
    }

}
