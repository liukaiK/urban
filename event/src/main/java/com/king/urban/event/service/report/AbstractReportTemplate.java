package com.king.urban.event.service.report;

import com.king.urban.event.entity.Event;
import com.king.urban.event.entity.Source;
import com.king.urban.event.pojo.report.ReportDTO;
import com.king.urban.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractReportTemplate {

    @Autowired
    private EventRepository eventRepository;

    public final void report(ReportDTO reportDTO) {
        Event event = new Event(getSource());



        event.updateAddress(reportDTO.getAddress());

        eventRepository.save(event);


    }


    protected abstract Source getSource();


}
