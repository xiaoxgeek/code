package com.controller;

import com.common.SpringTestBase;
import com.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by XiaoX on 2018/12/5.
 */
public class Hello extends SpringTestBase {
    @Autowired
    User user;
    @Test
    public void TestHello(){
        System.out.println(user.toString());
    }
}
