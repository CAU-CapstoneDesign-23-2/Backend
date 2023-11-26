package com.personal.doctor.CapstoneDesign.community.controller;

import com.personal.doctor.CapstoneDesign.community.controller.dto.PostAnsweredResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostListResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // post 저장
    @PostMapping("/post/{userId}")
    public Long savePost(@PathVariable Long userId,
                         @RequestBody PostSaveRequestDto requestDto) {
        return postService.save(userId, requestDto);
    }

    // post 수정
    @PutMapping("/post/{postId}")
    public Long updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(postId, requestDto);
    }

    // post 답변
    @PutMapping("/post/{userId}/{postId}")
    public Long answerPost(@PathVariable Long userId,
                           @PathVariable Long postId,
                           @RequestBody PostAnsweredResponseDto responseDto) {
        return postService.answered(userId, postId, responseDto);
    }

    // post 검색
    @GetMapping(value = "/post/search", produces = "application/json;charset=UTF-8")
    public List<PostListResponseDto> searchPost(@RequestParam("keyword") String keyword) {
        return postService.search(keyword);
    }

    // 모든 게시물 반환
    @GetMapping(value = "/posts", produces = "application/json;charset=UTF-8")
    public List<PostListResponseDto> findAllPosts() {
        return postService.findAllDesc();
    }

    // userId가 id인 사용자의 게시물 반환
    @GetMapping(value = "/posts/{userId}", produces = "application/json;charset=UTF-8")
    public List<PostListResponseDto> userPosts(@PathVariable Long userId) {
        return postService.findById(userId);
    }
}
