package com.personal.doctor.CapstoneDesign.chat;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.chat.service.ChatService;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ChatServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @AfterEach
    public void clean() {
        chatService.deleteAll();
        userService.deleteAll();
    }

    private static Long userId;

    @BeforeEach
    public void setup() {
        UserJoinRequestDto userJoinRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(userJoinRequestDto);
    }

    @Test
    public void 채팅_저장() {
        ChatSaveRequestDto chatSaveRequestDto = ChatSaveRequestDto.builder()
                .requestText("40세 남자인데 무릎이 너무 아파. 어떻게 해야할까?")
                .responseText("Bard 대답")
                .build();
        Long chatId = chatService.save(userId, chatSaveRequestDto);

        assertEquals(1, chatRepository.count());
    }

}
