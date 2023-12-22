package com.king.urban.main.event.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.BootstrapRegistry;
import org.springframework.boot.BootstrapRegistryInitializer;

public class EventBootstrapRegistryInitializer implements BootstrapRegistryInitializer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void initialize(BootstrapRegistry registry) {
        log.debug("initialize");
    }


}
