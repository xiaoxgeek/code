package com.xiaox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by XiaoX on 2018/11/18.
 */
@Controller
public class HelloController {

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "你好！");
        return "success";
    }
}
