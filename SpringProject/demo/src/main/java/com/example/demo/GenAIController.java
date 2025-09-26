package com.example.demo;

import com.example.demo.ChatService;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt){
        return chatService.getResponseOptions(prompt);
    }

    /*@GetMapping("generate-image")
    public void generateImages(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageService.generateImage(prompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }*/

    @GetMapping("generate-image")
    public List<String> generateImages(@RequestParam String prompt) {
        try {
            ImageResponse imageResponse = imageService.generateImage(prompt, "hd", 1, 1024, 1024);
            if (imageResponse == null || imageResponse.getResults() == null) {
                System.out.println("ImageResponse or results is null");
                return List.of();
            }

            List<String> imageUrls = imageResponse.getResults().stream()
                    .map(result -> {
                        if (result == null || result.getOutput() == null || result.getOutput().getUrl() == null)
                            return "";
                        return result.getOutput().getUrl();
                    })
                    .toList();

            return imageUrls;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // return empty list on error
        }
    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestriction) {
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }
}