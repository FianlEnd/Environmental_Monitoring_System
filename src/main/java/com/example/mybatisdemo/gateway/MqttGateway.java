package com.example.mybatisdemo.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;


@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    public abstract void sendMsgToMqtt(@Header(value = MqttHeaders.TOPIC) String topic , String payload) ;

    public abstract void sendMsgToMqtt(@Header(value = MqttHeaders.TOPIC) String topic , @Header(value = MqttHeaders.QOS) int qos ,  String payload) ;

}
