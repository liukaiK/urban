package com.king.urban.event.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EventApplicationListener implements ApplicationListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        log.debug("onApplicationEvent");
    }

}
