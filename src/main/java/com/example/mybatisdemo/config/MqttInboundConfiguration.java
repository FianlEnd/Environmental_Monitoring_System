package com.example.mybatisdemo.config;

import com.example.mybatisdemo.domain.MqttConfigurationProperties;
import com.example.mybatisdemo.handler.ReceiverMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttInboundConfiguration {

    @Autowired
    private MqttConfigurationProperties mqttConfigurationProperties ;

    @Autowired
    private MqttPahoClientFactory mqttPahoClientFactory ;

    @Autowired
    private ReceiverMessageHandler receiverMessageHandler ;

    // 消息通道
    @Bean
    public MessageChannel messageInboundChannel() {
        return new DirectChannel();
    }

    // 配置入站适配器，作用：设置订阅主题，以及指定消息的相关属性
    @Bean
    public MessageProducer messageProducer() {
        MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter = new MqttPahoMessageDrivenChannelAdapter(
                mqttConfigurationProperties.getUrl() ,
                mqttConfigurationProperties.getSubClientId() ,
                mqttPahoClientFactory ,
                mqttConfigurationProperties.getSubTopic().split(",")
        ) ;

        mqttPahoMessageDrivenChannelAdapter.setQos(1);
        mqttPahoMessageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        mqttPahoMessageDrivenChannelAdapter.setOutputChannel(messageInboundChannel());
        return mqttPahoMessageDrivenChannelAdapter ;
    }

    @Bean
    @ServiceActivator(inputChannel = "messageInboundChannel")
    public MessageHandler messageHandler() {
        return receiverMessageHandler ;
    }

}
