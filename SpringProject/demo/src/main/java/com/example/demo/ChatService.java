package com.example.demo;

import org.springframework.ai.chat.model.ChatModel;

public class ChatService {
    private final ChatModel chatModel;
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public String getMessage(String prompt) {
        return chatModel.call(prompt);
    }
}
