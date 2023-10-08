package com.king.urban.event;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AImpl implements A {

    private final List<ReportHandler> handlers = new ArrayList<>();

    @Override
    public void addHandler(ReportHandler reportHandler) {
        handlers.add(reportHandler);
    }

    @Override
    public List<ReportHandler> get() {
        return handlers;
    }

}
