package com.exam.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    //定义日志对象
    private static Logger logger = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        //级别为debug的日志
        logger.debug("Hello! debug!"); //级别为info的日志
        logger.info("Hello! info!"); //级别为warn的日志
        logger.warn("Hello! warn!"); //级别为error的日志
        logger.error("Hello! error!");
    }
}