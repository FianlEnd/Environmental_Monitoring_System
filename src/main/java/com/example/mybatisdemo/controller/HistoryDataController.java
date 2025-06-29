package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.*;
import com.example.mybatisdemo.mapper.HistoryDataMapper;
import com.example.mybatisdemo.service.HistoryDataService;
import com.example.mybatisdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Slf4j
public class HistoryDataController {
    @Autowired
    private HistoryDataService historyDataService;

    @GetMapping("/setThreshold")
    public Result threshold(@RequestParam Double threshold) {
        log.info("阈值设置请求: {}", threshold);
        try {
            int temp=historyDataService.setThreshold(threshold);
            if (temp!=0) {
                log.info("阈值设置成功: {}", threshold);
                return Result.success("阈值设置成功");
            }
            else{
                log.info("阈值设置失败");
                return Result.error("阈值设置失败");
            }
        }catch (Exception e){
            log.info("阈值设置失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/getThreshold")
    public Result getThreshold() {
        Double threshold = historyDataService.selectThreshold();
        if (threshold != null) {
            log.info("获取阈值成功: {}", threshold);
            return Result.success(threshold);
        } else {
            log.info("获取阈值失败,默认阈值");
            return Result.error("获取阈值失败");
        }
    }

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
