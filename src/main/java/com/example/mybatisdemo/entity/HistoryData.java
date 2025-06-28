package com.example.mybatisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @Description: 1,2,3依次是温度、湿度、可燃气体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryData {
    Double temperature;
    Double humidity;
    Double combustibleGas;

    LocalDateTime recordTime;
}

