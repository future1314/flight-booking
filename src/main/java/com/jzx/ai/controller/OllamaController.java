package com.jzx.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiangZhongxun
 * @version 1.0
 * @description: ollama 的测试
 * @email jiangzxun@126.com
 * @date 2024/5/7 20:15
 */
@RestController
public class OllamaController {

    @Resource
    private OllamaChatClient ollamaChatClient;

    /**
     * @description: 整合 ollama 实现简单聊天
     * @author JiangZhongxun
     * @date: 2024/5/7 20:21
     * @param prompt 提示词
     * @return String
     */
    @GetMapping("/ollama/ai/chat")
    public String ollamaChat( String prompt) {
        return ollamaChatClient.call(prompt);
    }

}
