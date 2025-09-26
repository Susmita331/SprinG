package com.example.demo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
@Service
public class RecipeService {
        private final ChatModel chatModel;

        public RecipeService(ChatModel chatModel) {
            this.chatModel = chatModel;
        }
}
