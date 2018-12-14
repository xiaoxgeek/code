package com.pojo;

import com.common.SpringTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by XiaoX on 2018/12/7.
 */
public class PerformanceTest extends SpringTestBase{
    @Autowired
    Performance performance;
    @Test
    public void test() {
        performance.perform();
    }
}
