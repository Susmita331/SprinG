package com.example.demo;

import org.springframework.ai.chat.model.ChatModel;

public class ChatService {
    private final ChatModel chatModel;
    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
}
