package com.king.urban.main.event.entity.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 立案
 *
 * @author liukai
 */
public class Lian extends State {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doAction() {
        logger.debug("处理立案状态");
    }

}
