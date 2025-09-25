package com.example.demo;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GenAIController {
    private final ChatService chatService;
    private final com.ai.SpringAiDemo.ImageService imageService;

    public GenAIController(ChatService chatService, com.ai.SpringAiDemo.ImageService imageService) {
        this.chatService = chatService;
        this.imageService = imageService;
    }
    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getMessage(prompt);
    }
    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getMessage(prompt);
    }
    @GetMapping("generate-image")
    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String ImageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(ImageUrl);
    }
}
