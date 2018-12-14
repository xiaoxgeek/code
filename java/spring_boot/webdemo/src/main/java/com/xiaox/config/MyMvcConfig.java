package com.xiaox.config;

import com.xiaox.interceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Created by XiaoX on 2018/11/25.
 */
@Configuration // 自定义配置
public class MyMvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置主页
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index").setViewName("login");
        registry.addViewController("/main").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 注册拦截器
        // 对静态资源的访问很有问题
        registry.addInterceptor(new LoginHandleInterceptor()).addPathPatterns("/**").excludePathPatterns("/index", "/index.html", "/", "/login", "/asserts/**", "/webjars/**");
    }
}
