package com.personal.doctor.CapstoneDesign.service;

import com.personal.doctor.CapstoneDesign.controller.dto.PostAnsweredResponseDto;
import com.personal.doctor.CapstoneDesign.controller.dto.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.PostUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import com.personal.doctor.CapstoneDesign.domain.posts.PostsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(SpringExtension.class)
class PostServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private PostsRepository postsRepository;

    @Test
    public void 게시물_저장수정답변() {
        // 저장
        UserJoinRequestDto user = UserJoinRequestDto.builder()
                .userID("userID")
                .userPassword("userPW")
                .build();
        Long userId = userService.join(user);
        PostSaveRequestDto post = PostSaveRequestDto.builder()
                .title("title")
                .category("category")
                .question("question")
                .build();
        Long postId = postService.save(userId, post);
        Posts savedSearchPost = postsRepository.findPostsById(postId).get();

        Assertions.assertEquals(post.getTitle(), savedSearchPost.getTitle());
        Assertions.assertEquals(post.getCategory(), savedSearchPost.getCategory());
        Assertions.assertEquals(post.getQuestion(), savedSearchPost.getQuestion());


        // 수정
        PostUpdateRequestDto updatePost = PostUpdateRequestDto.builder()
                .title("updated title")
                .question("updated question")
                .build();
        Long updatedPost = postService.update(userId, updatePost);
        Posts updatedSearchPost = postsRepository.findPostsById(updatedPost).get();

        Assertions.assertEquals(updatePost.getTitle(), updatedSearchPost.getTitle());
        Assertions.assertEquals(updatePost.getQuestion(), updatedSearchPost.getQuestion());


        // 답변
        PostAnsweredResponseDto answerPost = PostAnsweredResponseDto.builder()
                .docName("docName")
                .answer("answer")
                .build();
        Long answeredPost = postService.answered(postId, answerPost);
        Posts answeredSearchPost = postsRepository.findPostsById(answeredPost).get();

        Assertions.assertEquals(answerPost.getDocName(), answeredSearchPost.getDocName());
        Assertions.assertEquals(answerPost.getAnswer(), answeredSearchPost.getAnswer());
    }

}