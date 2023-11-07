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

    @GetMapping("/posts")
    public List<PostListResponseDto> findAllPosts() {
        return postService.findAllDesc();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto findPost(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping("/post")
    public Long savePost(@RequestBody PostSaveRequestDto requestDto) {
        return postService.save(requestDto);
    }

    @PutMapping("/post/update/{id}")
    public Long updatePost(@PathVariable Long id,
                           @RequestBody PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    @PostMapping("/post/answer/{id}")
    public Long answerPost(@PathVariable Long id,
                           @RequestBody PostAnsweredResponseDto responseDto) {
        return postService.answered(id, responseDto);
    }
}
