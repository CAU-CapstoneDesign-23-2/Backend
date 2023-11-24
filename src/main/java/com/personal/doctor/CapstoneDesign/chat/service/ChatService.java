package com.personal.doctor.CapstoneDesign.chat.service;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatRequestDto;
import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final UsersRepository usersRepository;
    private final ChatRepository chatRepository;

    public ChatService(UsersRepository usersRepository, ChatRepository chatRepository) {
        this.usersRepository = usersRepository;
        this.chatRepository = chatRepository;
    }

    // 채팅 저장
    @Transactional
    public Long save(Long userId, ChatSaveRequestDto chatSaveRequestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));
        Chat chat = chatSaveRequestDto.toEntity();

        users.addChats(chat);
        chatRepository.save(chat);

        return chat.getId();
    }

    // 사용자의 모든 채팅 반환
    @Transactional
    public List<ChatRequestDto> userChats(Long userId) {
        return chatRepository.findChatByUsersId(userId).stream()
                .map(ChatRequestDto::new)
                .collect(Collectors.toList());
    }

}
