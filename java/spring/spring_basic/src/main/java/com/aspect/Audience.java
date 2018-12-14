package com.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by XiaoX on 2018/12/7.
 */
@Component
@Aspect
public class Audience {
    @Pointcut("execution(* com.pojo.Performance.perform(..))")
    public void perform() {}

    @Before("perform()")
    public void silience() {
        System.out.println("开始了，手机静音。。。");
    }

    @After("perform()")
    public void leave() {
        System.out.println("结束了，离开会场。。。");
    }
}
