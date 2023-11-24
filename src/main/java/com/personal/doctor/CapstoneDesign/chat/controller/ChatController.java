package com.personal.doctor.CapstoneDesign.chat.controller;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.service.ChatService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 채팅 저장
    @PostMapping("/chat/{userId}")
    public Long save(@PathVariable Long userId,
                     @RequestBody ChatSaveRequestDto chatSaveRequestDto) {
        return chatService.save(userId, chatSaveRequestDto);
    }
}
