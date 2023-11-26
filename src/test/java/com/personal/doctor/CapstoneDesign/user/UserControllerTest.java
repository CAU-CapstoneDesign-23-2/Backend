package com.personal.doctor.CapstoneDesign.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    private static Long userId;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        UserJoinRequestDto requestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(requestDto);
    }

    @AfterEach
    public void clean() {
        userService.deleteAll();
    }

    @Test
    public void 사용자_회원가입() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userID", "userID");
        requestMap.put("userPassword", "userPassword");


        String content = new ObjectMapper().writeValueAsString(requestMap);
        mockMvc.perform(
                        post("/join")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 사용자_로그인() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userID", "ID");
        requestMap.put("userPassword", "PW");

        String content = new ObjectMapper().writeValueAsString(requestMap);
        mockMvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(userId)));
    }

    @Test
    public void 사용자_탈퇴() throws Exception {
        mockMvc.perform(
                        delete("/user/" + userId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(userId)));
    }

    @Test
    public void 사용자_수정() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userName", "수정된 이름");
        requestMap.put("location", "동작구");

        String content = new ObjectMapper().writeValueAsString(requestMap);
        mockMvc.perform(
                        put("/user/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(userId)));

        Users users = usersRepository.findById(userId).get();

        assertEquals("수정된 이름", users.getUserName());
        assertEquals("동작구", users.getLocation());
    }

    @Test
    public void 사용자_역할수정() throws Exception {
        mockMvc.perform(
                        put("/user/role/" + userId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(userId)));

        Users users = usersRepository.findById(userId).get();

        assertEquals("DOCTOR", users.getRole().toString());
    }

}