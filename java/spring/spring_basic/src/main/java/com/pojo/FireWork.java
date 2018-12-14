package com.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by XiaoX on 2018/12/7.
 */
@Component
public class FireWork implements Performance{
    @Override
    public void perform() {
        System.out.println("烟火表演。。。");
    }
}
