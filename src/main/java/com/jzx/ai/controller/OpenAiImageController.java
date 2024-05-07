package com.jzx.ai.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JiangZhongxun
 * @version 1.0
 * @description: 使用 OpenAi 生成图片
 * @email jiangzxun@126.com
 * @date 2024/5/7 19:36
 */
@RestController
public class OpenAiImageController {

    @Resource
    private OpenAiImageClient openAiImageClient;

    /**
     * @description: openAi 生成图片
     * @author JiangZhongxun
     * @date: 2024/5/7 20:09
     * @param prompt 提示词
     * @return String
     */
    @GetMapping(value = "/ai/image", produces = "text/html")
    public String generateImage(String prompt) {
        ImageResponse imageResponse = openAiImageClient.call(new ImagePrompt(prompt));
        Image image = imageResponse.getResult().getOutput();
        // 图片的 url 地址
        String url = image.getUrl();
        // 拿到图片的 base64 编码
        //String b64Json = image.getB64Json();
        return "<img src='" + url + "'>";
    }

}
