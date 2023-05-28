package com.king.urban.event.service.report;

import com.king.urban.event.entity.Event;
import com.king.urban.event.entity.Position;
import com.king.urban.event.entity.Source;
import com.king.urban.event.pojo.report.ReportDTO;
import com.king.urban.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractReportTemplate {

    @Autowired
    private EventRepository eventRepository;

    public final void report(ReportDTO reportDTO) {
        Source source = getSource();
        if (source == null) {
            throw new IllegalArgumentException("source cannot be null");
        }

        Position position = getPosition(reportDTO);

        Event event = new Event(source);

        event.updatePosition(position);

        eventRepository.save(event);


    }

    private Position getPosition(ReportDTO reportDTO) {
        return new Position(Double.valueOf(reportDTO.getLongitude()), Double.valueOf(reportDTO.getLatitude()), reportDTO.getAddress());
    }


    protected abstract Source getSource();


}
