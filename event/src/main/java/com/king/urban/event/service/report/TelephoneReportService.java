package com.king.urban.event.service.report;

import com.king.urban.event.entity.Event;
import com.king.urban.event.entity.Source;
import com.king.urban.event.pojo.report.ReportDTO;
import com.king.urban.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 热线上报
 *
 * @author liukai
 */
@Service
@Transactional
public class TelephoneReportService implements ReportService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void report(ReportDTO reportDTO) {
        Event event = new Event(Source.TELEPHONE);
        eventRepository.save(event);
    }

}
