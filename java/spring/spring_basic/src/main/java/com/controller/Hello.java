package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by XiaoX on 2018/12/4.
 */
@Controller
public class Hello {
    @GetMapping(value = "/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello World!");
        model.addAttribute("test", "Test");
        return "index";
    }
}
