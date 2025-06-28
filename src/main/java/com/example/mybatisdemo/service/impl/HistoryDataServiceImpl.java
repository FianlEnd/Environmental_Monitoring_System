package com.example.mybatisdemo.service.impl;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.entity.Result;
import com.example.mybatisdemo.mapper.HistoryDataMapper;
import com.example.mybatisdemo.service.HistoryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryDataServiceImpl implements HistoryDataService {
    @Autowired
    private HistoryDataMapper historyDataMapper;
    @Override
    public List<HistoryData> getHistoryData() {
        return historyDataMapper.getAllHistoryData();
    }
}
