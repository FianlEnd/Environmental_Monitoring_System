package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.entity.Result;
import com.example.mybatisdemo.mapper.HistoryDataMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author admin
 */
@RestController
@CrossOrigin(origins = "*")
public class AiController {


    /**
     * 上下文
     */
    private final List<Message> contextHistoryList = new ArrayList<>();

    @Autowired
    private HistoryDataMapper historyDataMapper;

    @Resource
    private OpenAiChatModel model;

    @PostConstruct
    public void init() {
        contextHistoryList.add(new SystemMessage("You are an environmental expert.You can receive information about temperature, humidity, combustible gases."));
        // 查询所有历史数据（按时间升序）
        List<HistoryData> allData = historyDataMapper.getAllHistoryData();

        // 取最后最多40条
        int startIndex = Math.max(0, allData.size() - 40);
        List<HistoryData> latestData = allData.subList(startIndex, allData.size());

        // 转化为字符串并加入上下文
        for (HistoryData data : latestData) {
            String dataStr = String.format(
                    "时间：%s，温度：%f，湿度：%f，可燃气体：%f",
                    data.getRecordTime(),
                    data.getTemperature(),
                    data.getHumidity(),
                    data.getCombustibleGas()
            );
            contextHistoryList.add(new SystemMessage(dataStr));
        }
    }

    /**
     * 普通对话
     *
     * @param message 问题
     * @return 回答结果（回答全部生成后一次性返回）
     */
    @GetMapping("/chat")
    public Result chat(String message) {
        contextHistoryList.add(new UserMessage(message));
        Prompt prompt = new Prompt(contextHistoryList);
        ChatResponse chatResp = model.call(prompt);
        Generation result = chatResp.getResult();
        if (Objects.nonNull(result) && Objects.nonNull(result.getOutput())) {
            contextHistoryList.add(result.getOutput());
        }
        System.out.println("成功调用ai");
        return Result.success(chatResp);
    }

    @GetMapping("/analysisChat")
    public Result analysisChat() {
        String message="给我你获得的数据的分析，只要分析数据，如果有需要提供可能的解决方案,把后面如需要进一步告知什么的话删掉";
        contextHistoryList.add(new UserMessage(message));
        Prompt prompt = new Prompt(contextHistoryList);
        ChatResponse chatResp = model.call(prompt);
        Generation result = chatResp.getResult();
        if (Objects.nonNull(result) && Objects.nonNull(result.getOutput())) {
            contextHistoryList.add(result.getOutput());
        }
        System.out.println("成功调用ai");
        return Result.success(chatResp);
    }

    /**
     * 流式返回
     *
     * @param message 问题
     * @return 流式结果（模型一边生成内容一边发送数据）
     */
    @GetMapping("/chat/v1")
    public Flux<ChatResponse> chatV1(String message) {
        contextHistoryList.add(new UserMessage(message));
        Prompt prompt = new Prompt(contextHistoryList);
        return model.stream(prompt);
    }
}
