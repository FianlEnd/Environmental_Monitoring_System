package com.example.mybatisdemo.task;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.mapper.HistoryDataMapper;
import com.example.mybatisdemo.mapper.UserMapper;
import com.example.mybatisdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class WarningTask {

    @Autowired
    private UserService userService;

    @Autowired
    private HistoryDataMapper historyDataMapper;

    // 直接定义阈值
    private static final double COMBUSTIBLE_GAS_THRESHOLD = 150.0;
    @Autowired
    private UserMapper userMapper;

    @Scheduled(cron = "*/10 * * * * ?")
    public void checkData() {
        log.info("定时任务开始执行...");

        HistoryData latestData = historyDataMapper.getNewData();
        if (latestData != null && latestData.getCombustibleGas() > COMBUSTIBLE_GAS_THRESHOLD) {
            log.warn("可燃性气体浓度超标: {}", latestData.getCombustibleGas());
            List<String> cityManagerEmails = userMapper.getAllCityManagerEmails();

            if (cityManagerEmails != null && !cityManagerEmails.isEmpty()) {
                for (String email : cityManagerEmails) {
                    String content = " 可燃性气体浓度超标"+"当前气体浓度为: " + latestData.getCombustibleGas();
                    userService.sendEmail(email, content);
                    log.info("已发送警告邮件至: {}", email);
                }
            } else {
                log.warn("未找到任何城市管理员邮箱");
            }
        } else {
            log.info("当前气体浓度正常");
        }
    }
}
