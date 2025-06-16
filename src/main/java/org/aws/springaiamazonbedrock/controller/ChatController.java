package org.aws.springaiamazonbedrock.controller;

import org.aws.springaiamazonbedrock.model.ChatRequest;
import org.aws.springaiamazonbedrock.model.ChatResponse;
import org.aws.springaiamazonbedrock.model.CityTemperatureRequest;
import org.aws.springaiamazonbedrock.model.CityTemperatureResponse;
import org.aws.springaiamazonbedrock.service.SimpleChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final SimpleChatServiceImpl chatService;

    public ChatController(SimpleChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/api/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String response = chatService.chat(request.getPrompt());
        return ResponseEntity.ok(new ChatResponse(response));
    }

    @PostMapping("/api/temperature/{city}")
    public ResponseEntity<ChatResponse> chatWithTemplate(@PathVariable String city) {
        String response = chatService.chatWithTemplate(city);
        return ResponseEntity.ok(new ChatResponse(response));
    }

    @PostMapping("/api/temperature/format")
    public ResponseEntity<CityTemperatureResponse> chatWithFormat(@RequestBody CityTemperatureRequest cityTemperatureRequest) {;
        return ResponseEntity.ok(chatService.chatWithFormat(cityTemperatureRequest));
    }
}
