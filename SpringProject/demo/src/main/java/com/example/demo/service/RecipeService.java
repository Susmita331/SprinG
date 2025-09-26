package com.example.demo.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
@Service
public class RecipeService {
        private final ChatModel chatModel;

        public RecipeService(ChatModel chatModel) {
            this.chatModel = chatModel;
        }
        public String createRecipe(String ingredients,
                               String cuisine,
                               String dietaryRestrictions) {
            var template = """
                I want to create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide me with a detailed recipe including title, list of ingredients, and cooking instructions
                """;

        }
}
