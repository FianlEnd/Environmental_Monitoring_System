package com.example.mybatisdemo.service;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.entity.Result;

import java.util.List;

public interface HistoryDataService {
      List<HistoryData> getHistoryData();
      HistoryData getNewData();
}
