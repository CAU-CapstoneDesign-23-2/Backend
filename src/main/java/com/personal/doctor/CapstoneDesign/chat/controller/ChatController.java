package com.personal.doctor.CapstoneDesign.chat.controller;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatRequestDto;
import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.service.ChatService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // 채팅 저장
    @PostMapping(value = "/chat/{userId}", produces = "application/json;charset=UTF-8")
    public Long save(@PathVariable Long userId,
                     @RequestBody ChatSaveRequestDto chatSaveRequestDto) {
        return chatService.save(userId, chatSaveRequestDto);
    }

    // 사용자의 모든 채팅 반환
    @PostMapping(value = "/userChats/{userId}", produces = "application/json;charset=UTF-8")
    public List<ChatRequestDto> userChats(@PathVariable Long userId) {
        return chatService.userChats(userId);
    }

}
