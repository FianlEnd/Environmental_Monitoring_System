package com.example.mybatisdemo.config;

import com.example.mybatisdemo.domain.MqttConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttOutboundConfiguration {

    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties ;

    @Autowired
    private MqttPahoClientFactory mqttPahoClientFactory ;

    // 消息通道
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel() ;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutboundMessageHandler() {
        // public MqttPahoMessageHandler(String url, String clientId, MqttPahoClientFactory clientFactory)
        MqttPahoMessageHandler mqttPahoMessageHandler = new MqttPahoMessageHandler(mqttConfigurationProperties.getUrl() ,
                mqttConfigurationProperties.getPubClientId() , mqttPahoClientFactory) ;
        mqttPahoMessageHandler.setDefaultQos(0);
        mqttPahoMessageHandler.setDefaultTopic("default");
        mqttPahoMessageHandler.setAsync(true);
        return mqttPahoMessageHandler ;
    }

}
