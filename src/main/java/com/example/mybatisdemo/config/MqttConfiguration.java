package com.example.mybatisdemo.config;

import com.example.mybatisdemo.domain.MqttConfigurationProperties;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@Configuration
public class MqttConfiguration {

    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties ;

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory mqttPahoClientFactory = new DefaultMqttPahoClientFactory() ;
        MqttConnectOptions options = new MqttConnectOptions() ;
        options.setCleanSession(true);
        options.setUserName(mqttConfigurationProperties.getUsername());
        options.setPassword(mqttConfigurationProperties.getPassword().toCharArray());
        options.setServerURIs(new String[]{ mqttConfigurationProperties.getUrl() } );
        mqttPahoClientFactory.setConnectionOptions(options);
        return mqttPahoClientFactory ;
    }

}
