package com.example.mybatisdemo.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttConfigurationProperties {

    private String username ;
    private String password ;
    private String url ;
    private String subClientId ;
    private String subTopic ;
    private String pubClientId ;

}
