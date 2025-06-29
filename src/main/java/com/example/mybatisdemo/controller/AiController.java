package com.example.mybatisdemo.controller;

import com.example.mybatisdemo.entity.HistoryData;
import com.example.mybatisdemo.entity.Result;
import com.example.mybatisdemo.mapper.HistoryDataMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import com.example.mybatisdemo.config.CommonConfiguration;
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
//    private final List<Message> contextHistoryList = new ArrayList<>();

    @Autowired
    private HistoryDataMapper historyDataMapper;

    @Resource
    private OpenAiChatModel model;

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public Result chat(@RequestParam("prompt") String prompt) {
        List<HistoryData> data = historyDataMapper.getAllHistoryData();
        // 构建包含数据库信息的提示词
        String context = "这是近期40条的环境数据：\n" + data.toString() + "\n\n用户问：" + prompt;
        String message= chatClient.prompt()
                .user(context)
                .call()
                .content();
        return Result.success(message);
    }


    @GetMapping("/analysisChat")
    public Result analysisChat() {
        List<HistoryData> data = historyDataMapper.getAllHistoryData();
        // 构建包含数据库信息的提示词
        String context = "这是近期50条的环境数据：\n" + data.toString() +"\n\n请分析这些数据,最后不要加类似有什么再问我这种意思的话";
        String message= chatClient.prompt()
                .user(context)
                .call()
                .content();
        return Result.success(message);
    }
//    @GetMapping("/chat")
//    public Result chat(String message) {
//        // 1. 查询数据库获取最新数据（例如取最后20条）
//        List<HistoryData> allData = historyDataMapper.getAllHistoryData();
//        int startIndex = Math.max(0, allData.size() - 20);
//        List<HistoryData> latestData = allData.subList(startIndex, allData.size());
//
//        // 2. 构建数据库信息字符串
//        StringBuilder dbInfo = new StringBuilder("以下是最近的环境数据：\n");
//        for (HistoryData data : latestData) {
//            dbInfo.append(String.format(
//                    "时间：%s，温度：%.2f，湿度：%.2f，可燃气体：%.2f\n",
//                    data.getRecordTime(),
//                    data.getTemperature(),
//                    data.getHumidity(),
//                    data.getCombustibleGas()
//            ));
//        }
//
//        // 3. 将数据库信息拼接到用户输入前
//        String finalPrompt = dbInfo.toString() + "\n用户问题：" + message;
//
//        // 4. 构造 Prompt 并调用 AI 模型
//        Prompt prompt = new Prompt(new UserMessage(finalPrompt));
//        ChatResponse chatResp = model.call(prompt);
//        Generation result = chatResp.getResult();
//        if(result==null){return Result.error("没有结果");}
//        System.out.println("成功调用ai");
//        return Result.success(result.getOutput());
//    }

//    @GetMapping("/analysisChat")
//    public Result analysisChat() {
//        // 查询数据库获取最新数据
//        List<HistoryData> allData = historyDataMapper.getAllHistoryData();
//        int startIndex = Math.max(0, allData.size() - 20);
//        List<HistoryData> latestData = allData.subList(startIndex, allData.size());
//
//        // 构建数据库信息字符串
//        StringBuilder dbInfo = new StringBuilder("以下是最近的环境数据：\n");
//        for (HistoryData data : latestData) {
//            dbInfo.append(String.format(
//                    "时间：%s，温度：%.2f，湿度：%.2f，可燃气体：%.2f\n",
//                    data.getRecordTime(),
//                    data.getTemperature(),
//                    data.getHumidity(),
//                    data.getCombustibleGas()
//            ));
//        }
//
//        // 固定指令 + 数据
//        String finalPrompt = dbInfo.toString() + "\n请对以上数据进行深入分析，指出潜在风险并提供解决方案，最后不要加类似有什么再问我这种意思的话";
//
//        // 调用 AI
//        Prompt prompt = new Prompt(new UserMessage(finalPrompt));
//        ChatResponse chatResp = model.call(prompt);
//        Generation result = chatResp.getResult();
//
//        if(result==null){return Result.error("没有结果");}
//        System.out.println("成功调用ai");
//        return Result.success(result.getOutput());
//    }







//    @PostConstruct
//    public void init() {
//        contextHistoryList.add(new SystemMessage("You are an environmental expert.You can receive information about temperature, humidity, combustible gases."));
//        // 查询所有历史数据（按时间升序）
//        List<HistoryData> allData = historyDataMapper.getAllHistoryData();
//
//        // 取最后最多40条
//        int startIndex = Math.max(0, allData.size() - 40);
//        List<HistoryData> latestData = allData.subList(startIndex, allData.size());
//
//        // 转化为字符串并加入上下文
//        for (HistoryData data : latestData) {
//            String dataStr = String.format(
//                    "时间：%s，温度：%f，湿度：%f，可燃气体：%f",
//                    data.getRecordTime(),
//                    data.getTemperature(),
//                    data.getHumidity(),
//                    data.getCombustibleGas()
//            );
//            contextHistoryList.add(new SystemMessage(dataStr));
//        }
//    }

    /**
     * 普通对话
     *
     * @param message 问题
     * @return 回答结果（回答全部生成后一次性返回）
     */
//    @GetMapping("/chat")
//    public Result chat(String message) {
//        contextHistoryList.add(new UserMessage(message));
//        Prompt prompt = new Prompt(contextHistoryList);
//        ChatResponse chatResp = model.call(prompt);
//        Generation result = chatResp.getResult();
//        if (Objects.nonNull(result) && Objects.nonNull(result.getOutput())) {
//            contextHistoryList.add(result.getOutput());
//        }
//        System.out.println("成功调用ai");
//        return Result.success(chatResp);
//    }
//
//    @GetMapping("/analysisChat")
//    public Result analysisChat() {
//        String message="给我你获得的数据的分析，只要分析数据，如果有需要提供可能的解决方案,把后面如需要进一步告知什么的话删掉";
//        contextHistoryList.add(new UserMessage(message));
//        Prompt prompt = new Prompt(contextHistoryList);
//        ChatResponse chatResp = model.call(prompt);
//        Generation result = chatResp.getResult();
//        if (Objects.nonNull(result) && Objects.nonNull(result.getOutput())) {
//            contextHistoryList.add(result.getOutput());
//        }
//        System.out.println("成功调用ai");
//        return Result.success(chatResp);
//    }

    /**
     * 流式返回
     *
     * @param message 问题
     * @return 流式结果（模型一边生成内容一边发送数据）
     */
//    @GetMapping("/chat/v1")
//    public Flux<ChatResponse> chatV1(String message) {
//        contextHistoryList.add(new UserMessage(message));
//        Prompt prompt = new Prompt(contextHistoryList);
//        return model.stream(prompt);
//    }
}
