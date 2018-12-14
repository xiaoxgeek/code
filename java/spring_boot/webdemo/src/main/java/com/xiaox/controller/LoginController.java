package com.xiaox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by XiaoX on 2018/11/25.
 */
@Controller
public class LoginController {

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && "admin".equals(username) && "123456".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main";
        } else {
            map.put("msg", "用户名或密码错误！");
            return "login";
        }
    }
}
