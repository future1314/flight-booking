package com.jzx.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author JiangZhongxun
 * @version 1.0
 * @description: 使用 OpenAi 进行简单聊天
 * @email jiangzxun@126.com
 * @date 2024/5/7 18:56
 */
@RestController
public class OpenAiChatController {

    @Resource
    private OpenAiChatClient openAiChatClient;

    /**
     * @description: 演示整合 OpenAi 的简单使用
     * @author JiangZhongxun
     * @date: 2024/5/7 19:28
     * @param message 消息
     * @return String
     */
    @GetMapping("/ai/chat")
    public String chat(String message) {
        // 自动连接上 openAi 网站，利用配置文件中配置的模型发送请求
        return openAiChatClient.call(message);
    }

    /**
     * @description: 按照流数据返回结果
     * @author JiangZhongxun
     * @date: 2024/5/7 19:28
     * @param message 消息
     * @return Flux<String>
     */
    @GetMapping(value = "/ai/chatStream"/*, produces = MediaType.TEXT_EVENT_STREAM_VALUE*/)
    public Flux<String> chatStream(String message) {
        // 自动连接上 openAI 网站，利用配置文件中配置的模型发送请求
        return openAiChatClient.stream(message);
    }

}
