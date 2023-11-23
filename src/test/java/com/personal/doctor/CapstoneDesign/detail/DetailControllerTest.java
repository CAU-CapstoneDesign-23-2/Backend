package com.personal.doctor.CapstoneDesign.detail;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsResponseDto;
import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.detail.domain.Details;
import com.personal.doctor.CapstoneDesign.detail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.detail.service.DetailsService;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class DetailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private DetailsRepository detailsRepository;

    private static Long userId;

    @BeforeEach
    public void setup() {
        UserJoinRequestDto userRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(userRequestDto);
    }

    @AfterEach
    public void clean() {
        detailsRepository.deleteAll();
        detailsService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void 세부정보_저장() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("age", "81");
        requestMap.put("gender", "남자");
        requestMap.put("disease1", "맹장");
        requestMap.put("disease2", "고혈압");
        requestMap.put("surgery", "간 이식");
        requestMap.put("hobby1", "축구");
        requestMap.put("hobby2", "야구");
        requestMap.put("medicine", "고혈압 약");
        String content = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc.perform(
                        post("/detail/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk());

        Details userDetails = detailsRepository.findUserDetails(userId);

        assertEquals("81", userDetails.getAge());
        assertEquals("남자", userDetails.getGender());
        assertEquals("맹장", userDetails.getDisease1());
        assertEquals("고혈압", userDetails.getDisease2());
        assertEquals("간 이식", userDetails.getSurgery());
        assertEquals("축구", userDetails.getHobby1());
        assertEquals("야구", userDetails.getHobby2());
        assertEquals("고혈압 약", userDetails.getMedicine());
    }

    @Test
    public void 세부정보_수정() throws Exception {
        DetailsSaveRequestDto saveRequestDto = DetailsSaveRequestDto.builder()
                .age("72")
                .gender("여자")
                .disease1("골다공증")
                .hobby1("수영")
                .hobby2("탁구")
                .hobby3("등산")
                .build();
        Long detailId = detailsService.save(userId, saveRequestDto);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("age", "73");
        requestMap.put("gender", "여자");
        requestMap.put("disease1", "골다공증");
        requestMap.put("disease2", "허리 디스크");
        requestMap.put("disease3", "목 디스크");
        requestMap.put("hobby1", "수영");
        requestMap.put("hobby2", "탁구");
        requestMap.put("hobby3", "산책");
        requestMap.put("medicine", "영양제");
        String content = new ObjectMapper().writeValueAsString(requestMap);

        mockMvc.perform(
                        put("/detail/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk());

        Details userDetails = detailsRepository.findUserDetails(userId);

        assertEquals("73", userDetails.getAge());
        assertEquals("여자", userDetails.getGender());
        assertEquals("골다공증", userDetails.getDisease1());
        assertEquals("허리 디스크", userDetails.getDisease2());
        assertEquals("목 디스크", userDetails.getDisease3());
        assertEquals("수영", userDetails.getHobby1());
        assertEquals("탁구", userDetails.getHobby2());
        assertEquals("산책", userDetails.getHobby3());
        assertEquals("영양제", userDetails.getMedicine());
    }

    @Test
    public void 세부정보_반환() throws Exception {
        DetailsSaveRequestDto saveRequestDto = DetailsSaveRequestDto.builder()
                .age("72")
                .gender("여자")
                .disease1("골다공증")
                .hobby1("수영")
                .hobby2("탁구")
                .hobby3("등산")
                .build();
        Long detailId = detailsService.save(userId, saveRequestDto);

        MvcResult mvcResult = mockMvc.perform(
                        get("/detail/" + userId)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        DetailsResponseDto detail = new ObjectMapper().readValue(jsonResponse, new TypeReference<DetailsResponseDto>() {});

        assertEquals("72", detail.getAge());
        assertEquals("여자", detail.getGender());
        assertEquals("골다공증", detail.getDisease1());
        assertEquals("수영", detail.getHobby1());
        assertEquals("탁구", detail.getHobby2());
        assertEquals("등산", detail.getHobby3());
    }

}