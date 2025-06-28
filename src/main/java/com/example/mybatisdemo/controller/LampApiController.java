package com.example.mybatisdemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.mybatisdemo.service.MqttMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/lamp")
public class LampApiController {

    @Autowired
    private MqttMessageSender mqttMessageSender ;

    @GetMapping(value = "/{deviceId}/{status}")
    public String sendStatusLampMsg(@PathVariable(value = "deviceId") String deviceId , @PathVariable(value = "status") Integer status ) {
        Map<String , Object> map = new HashMap<>() ;
        map.put("deviceId",deviceId ) ;
        map.put("status" , status) ;
        String json = JSON.toJSONString(map);
//      mqttMessageSender.sendMsg("atguigu/iot/lamp/server/status" , json);
        mqttMessageSender.sendMsg("temperature" , json);
        return "ok" ;
    }
}
