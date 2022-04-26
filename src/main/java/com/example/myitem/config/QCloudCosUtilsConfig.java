package com.example.myitem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QCloudCosUtilsConfig {
    @ConfigurationProperties(prefix = "spring.qcloud")
    @Bean
    public QCloudCosUtils qcloudCosUtils() {
        return new QCloudCosUtils();
    }
}
