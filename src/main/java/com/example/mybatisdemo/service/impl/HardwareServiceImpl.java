package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.mapper.HardwareMapper;
import com.example.mybatisdemo.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HardwareServiceImpl implements HardwareService{
    @Autowired
    private HardwareMapper HardwareMapper;

    @Override
    public void recordMessage(String payload){
        String[] values = payload.split(";");
        if (values.length == 3) {
            Double temperature = Double.parseDouble(values[0]); // 温度值
            Double humidity = Double.parseDouble(values[1]);    // 湿度值
            Double combustibleGas = Double.parseDouble(values[2]); // 可燃气体值
            System.out.println("开始执行sql" +"温度，湿度，可燃气体： "+payload);
            HardwareMapper.recordMessage(temperature,humidity,combustibleGas);
        } else {
            // 处理异常情况，例如日志记录或抛出异常
            System.out.println("传入数据不是分号分隔的三个数据");
        }


    }
}
