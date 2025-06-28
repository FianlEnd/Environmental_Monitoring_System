package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.*;
import com.example.mybatisdemo.service.HistoryDataService;
import com.example.mybatisdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class HistoryDataController {
    @Autowired
    private HistoryDataService historyDataService;

    @GetMapping("/historyData")
    public Result GetHistoryDataController() {
        List<HistoryData> data = historyDataService.getHistoryData();
        return Result.success(data);
    }

    // 获取最新数据
    @GetMapping("/getLatestData")
    public Result GetNewDataController() {
        HistoryData data = historyDataService.getNewData();
        return Result.success(data);
    }
}
