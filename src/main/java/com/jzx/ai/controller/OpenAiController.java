package com.jzx.ai.controller;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @author JiangZhongxun
 * @version 1.0
 * @description:
 * @email jiangzxun@126.com
 * @date 2024/5/1 19:06
 */
@RestController
@CrossOrigin
public class OpenAiController {

    private final OpenAiChatClient chatClient;

    @Autowired
    public OpenAiController(OpenAiChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        return Map.of("generation", chatClient.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStreamAsChatResponse(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return chatClient.stream(prompt);
    }

    @CrossOrigin
    @GetMapping(value = "/ai/generateStreamAsString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStreamAsString(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        //Prompt prompt = new Prompt(new UserMessage(message));
        //return chatClient.stream(prompt);
        Flux<String> stream = chatClient.stream(message);
        return stream;
    }

}
