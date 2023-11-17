package com.personal.doctor.CapstoneDesign.service;

import com.personal.doctor.CapstoneDesign.controller.dto.posts.PostAnsweredResponseDto;
import com.personal.doctor.CapstoneDesign.controller.dto.posts.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.posts.PostUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.users.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import com.personal.doctor.CapstoneDesign.domain.posts.PostsRepository;
import com.personal.doctor.CapstoneDesign.util.PostNOTExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void deleteAll() {
        postsRepository.deleteAll();
        postService.deleteAll();
        userService.deleteAll();
    }

    @Test
    @Rollback
    public void 게시물_저장() {
        UserJoinRequestDto userRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long userID = userService.join(userRequestDto);

        PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
                .title("title")
                .category("category")
                .question("question")
                .build();
        Long postID = postService.save(userID, postRequestDto);

        Posts posts = postsRepository.findById(postID)
                .orElseThrow(() -> new PostNOTExistException("게시물이 존재하지 않습니다."));

        Assertions.assertEquals("title", posts.getTitle());
    }

    @Test
    @Rollback
    public void 게시물_수정() {
        UserJoinRequestDto userRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long userID = userService.join(userRequestDto);

        PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
                .title("title")
                .category("category")
                .question("question1")
                .build();
        Long postID = postService.save(userID, postRequestDto);

        PostUpdateRequestDto updateRequestDto = PostUpdateRequestDto.builder()
                .title("title")
                .question("question2")
                .build();
        postService.update(postID, updateRequestDto);

        Posts posts = postsRepository.findById(postID)
                .orElseThrow(() -> new PostNOTExistException("게시물이 존재하지 않습니다."));

        Assertions.assertEquals("question2", posts.getQuestion());
    }

    @Test
    @Rollback
    public void 게시물_답변() {
        UserJoinRequestDto userRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long userID = userService.join(userRequestDto);

        PostSaveRequestDto postRequestDto = PostSaveRequestDto.builder()
                .title("title")
                .category("category")
                .question("question1")
                .build();
        Long postID = postService.save(userID, postRequestDto);

        PostAnsweredResponseDto answeredResponseDto = PostAnsweredResponseDto.builder()
                .answer("answer")
                .docName("docName")
                .build();
        postService.answered(postID, answeredResponseDto);

        Posts posts = postsRepository.findById(postID)
                .orElseThrow(() -> new PostNOTExistException("게시물이 존재하지 않습니다."));

        Assertions.assertEquals("docName", posts.getDocName());
    }

}