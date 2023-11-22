package com.personal.doctor.CapstoneDesign.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.community.domain.PostsRepository;
import com.personal.doctor.CapstoneDesign.community.service.PostService;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import com.personal.doctor.CapstoneDesign.util.exceptions.PostNOTExistException;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.aspectj.lang.annotation.After;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    private static Long userId;
    private static Long postId;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        UserJoinRequestDto userRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(userRequestDto);

        PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
                .title("title")
                .category("category")
                .question("question")
                .build();
        postId = postService.save(userId, postRequestDto);
    }

    @AfterEach
    public void clean() {
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void 게시물_저장() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("title", "title");
        requestMap.put("category", "category");
        requestMap.put("question", "question");
        String content = new ObjectMapper().writeValueAsString(requestMap);

        MvcResult mvcResult = mockMvc.perform(
                        post("/post/" + userId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Long postIdTest = Long.parseLong(mvcResult.getResponse().getContentAsString());
        System.out.println("RESPONSE: " + postIdTest);

        Posts posts = postsRepository.findById(postIdTest).get();

        assertEquals("title", posts.getTitle());
        assertEquals("category", posts.getCategory());
        assertEquals("question", posts.getQuestion());
    }

    @Test
    public void 게시물_수정() throws Exception {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("title", "updateTitle");
        requestMap.put("question", "updateQuestion");
        String content = new ObjectMapper().writeValueAsString(requestMap);

        MvcResult mvcResult = mockMvc.perform(
                        put("/post/update/" + postId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(content)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Long postIdTest = Long.parseLong(mvcResult.getResponse().getContentAsString());

        Posts posts = postsRepository.findById(postIdTest).get();

        assertEquals("updateTitle", posts.getTitle());
        assertEquals("category", posts.getCategory());
        assertEquals("updateQuestion", posts.getQuestion());
    }

}