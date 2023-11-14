package com.personal.doctor.CapstoneDesign.controller;

import com.personal.doctor.CapstoneDesign.controller.dto.*;
import com.personal.doctor.CapstoneDesign.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 모든 게시물 반환
    @GetMapping("/posts")
    public List<PostListResponseDto> findAllPosts() {
        return postService.findAllDesc();
    }

    // userId가 id인 사용자의 게시물 반환
    @GetMapping("/posts/user/{id}")
    public List<PostListResponseDto> userPosts(@PathVariable Long id) {
        return postService.findById(id);
    }

    // post 저장
    @PostMapping("/post/{id}")
    public Long savePost(@PathVariable Long id,
                         @RequestBody PostSaveRequestDto requestDto) {
        return postService.save(id, requestDto);
    }

    // post 수정
    @PutMapping("/post/update/{id}")
    public Long updatePost(@PathVariable Long id,
                           @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // post 답변
    @PostMapping("/post/answer/{id}")
    public Long answerPost(@PathVariable Long id,
                           @RequestBody PostAnsweredResponseDto responseDto) {
        return postService.answered(id, responseDto);
    }
}
