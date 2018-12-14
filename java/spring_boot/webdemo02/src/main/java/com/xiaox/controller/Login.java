package com.xiaox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XiaoX on 2018/11/25.
 */
@Controller
public class Login {
    @RequestMapping("/index")
    public String login() {
        return "login";
    }
}
