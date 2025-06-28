package com.example.mybatisdemo.handler;

import com.example.mybatisdemo.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMessageHandler implements MessageHandler {

    @Autowired
    private HardwareService hardwareService;
    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String payload = message.getPayload().toString();
        MessageHeaders headers = message.getHeaders();
        String topicName = headers.get("mqtt_receivedTopic").toString();
        //TODO 根据 topicName 的内容做相应业务处理
        if("7data".equals(topicName) ) {
            hardwareService.recordMessage(payload);
        }
        System.out.println("received headers: " + headers);
        System.out.println("received topic:   " + topicName);
        System.out.println("received payload: " + payload);
    }

}
