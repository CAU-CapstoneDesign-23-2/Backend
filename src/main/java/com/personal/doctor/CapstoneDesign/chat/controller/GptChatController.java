package com.personal.doctor.CapstoneDesign.chat.controller;

import com.personal.doctor.CapstoneDesign.chat.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gpt")
public class GptChatController {

    private final ChatService chatService;

    public GptChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{userId}")
    public String getGptResponse(@PathVariable Long userId, @RequestParam String text) {
        return chatService.handleGptRequest(userId, text);
    }
}
