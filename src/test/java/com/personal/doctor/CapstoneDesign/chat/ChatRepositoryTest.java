package com.personal.doctor.CapstoneDesign.chat;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @AfterEach
    public void clean() {
        chatRepository.deleteAll();
    }

    @Test
    public void 채팅_저장() {
        Chat chat = Chat.builder()
                .type(0L)
                .content("40세 남자인데 무릎이 너무 아파. 어떻게 해야할까?")
                .build();
        chatRepository.save(chat);

        assertEquals(1, chatRepository.count());
    }

}
