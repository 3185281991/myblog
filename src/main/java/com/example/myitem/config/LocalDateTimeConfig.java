package com.example.myitem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LocalDateTimeConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ///myblogImg/**只对外暴露的路径
        //"file:D:/image/"内部文件存放位置
        //如此设置后即可浏览器输入信息localhost:8080/myblog/图片名称即可访问
        registry.addResourceHandler("/myblogImg/**").addResourceLocations("file:D:/image/");
    }
}



