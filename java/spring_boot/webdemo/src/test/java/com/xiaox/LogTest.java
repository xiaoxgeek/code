package com.xiaox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by XiaoX on 2018/12/6.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LogTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void contextLoader() {
        logger.trace("这是一个trace...");
        logger.debug("这是一个debug...");
        logger.info("这是一个info...");
        logger.warn("这是一个warn...");
        logger.error("这是一个error...");
    }
}
