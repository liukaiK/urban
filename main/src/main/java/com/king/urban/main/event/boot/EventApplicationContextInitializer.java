package com.king.urban.main.event.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class EventApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

    }

}
