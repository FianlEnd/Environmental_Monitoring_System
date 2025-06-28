package com.example.mybatisdemo;

import com.example.mybatisdemo.domain.MqttConfigurationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring Boot MyBatis演示应用程序
 */
//@EnableConfigurationProperties(value = MqttConfigurationProperties.class)
//@MapperScan("com.example.mybatisdemo.mapper")
@SpringBootApplication
@ServletComponentScan //开启了SpringBoot对Servlet组件的支持
@MapperScan("com.example.mybatisdemo.mapper")
@EnableScheduling
public class MybatisDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisDemoApplication.class, args);
        System.out.println("环境检测程序启动成功！");
    }
} 