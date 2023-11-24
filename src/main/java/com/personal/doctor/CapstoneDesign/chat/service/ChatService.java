package com.personal.doctor.CapstoneDesign.chat.service;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    private final UsersRepository usersRepository;
    private final ChatRepository chatRepository;

    public ChatService(UsersRepository usersRepository, ChatRepository chatRepository) {
        this.usersRepository = usersRepository;
        this.chatRepository = chatRepository;
    }

    @Transactional
    public Long save(Long userId, ChatSaveRequestDto chatSaveRequestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));
        Chat chat = chatSaveRequestDto.toEntity();

        users.addChats(chat);
        chatRepository.save(chat);

        return chat.getId();
    }

}
