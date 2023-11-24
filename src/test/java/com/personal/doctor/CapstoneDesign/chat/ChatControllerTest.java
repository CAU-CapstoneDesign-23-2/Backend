package com.personal.doctor.CapstoneDesign.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.chat.service.ChatService;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

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
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        UserJoinRequestDto userJoinRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(userJoinRequestDto);
    }

    @Test
    public void 채팅_저장() throws Exception {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("type", 0L);
        requestMap.put("content", "채팅 저장 api 테스트");
        String content = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc.perform(
                        post("/chat/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk());

        Chat chat = chatRepository.findById(userId).get();

        assertEquals(0L, chat.getType());
        assertEquals("채팅 저장 api 테스트", chat.getContent());
    }

}
