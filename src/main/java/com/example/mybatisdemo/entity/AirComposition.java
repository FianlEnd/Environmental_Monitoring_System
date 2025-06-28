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
public class AirComposition {
    private List<String> payload1;
    private List<String> payload2;
    private List<String> payload3;

    private List<LocalDateTime> time1;
    private List<LocalDateTime> time2;
    private List<LocalDateTime> time3;
}

