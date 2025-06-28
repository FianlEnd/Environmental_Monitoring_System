package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.service.MqttMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mqtt")
public class MqttToolController {

    @Autowired
    private MqttMessageSender mqttMessageSender ;

//    @GetMapping(value = "/send/{topic}/{msg}")
//    public String sendStatusLampMsg(@PathVariable(value = "topic") String toppic , @PathVariable(value = "msg") String msg ) {
//        mqttMessageSender.sendMsg(toppic , msg);
//        return "ok" ;
//    }
    @GetMapping(value = "/send")
    public String sendStatusLampMsg(
            @RequestParam("topic") String topic,
            @RequestParam("msg") String msg) {
        mqttMessageSender.sendMsg(topic, msg);
        return "ok";
    }

}
