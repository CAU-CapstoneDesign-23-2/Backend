package com.personal.doctor.CapstoneDesign.chat.service;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Transactional
    public Long save(ChatSaveRequestDto chatSaveRequestDto) {
        Chat chat = chatSaveRequestDto.toEntity();
        chatRepository.save(chat);

        return chat.getId();
    }

}
