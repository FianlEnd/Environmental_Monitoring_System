package com.example.mybatisdemo.service;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.entity.Result;

import java.util.List;

public interface HistoryDataService {

    Double selectThreshold();

    int setThreshold(Double threshold);

    List<HistoryData> getHistoryData();

    HistoryData getNewData();
}
