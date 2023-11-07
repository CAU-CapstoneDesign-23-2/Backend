package com.personal.doctor.CapstoneDesign.service;

import com.personal.doctor.CapstoneDesign.controller.dto.*;
import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import com.personal.doctor.CapstoneDesign.domain.posts.PostsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostsRepository postsRepository;

    public PostService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다. id=" + id));
        posts.updatePosts(requestDto.getTitle(), requestDto.getQuestion());

        return id;
    }

    @Transactional
    public Long answered(Long id, PostAnsweredResponseDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다. id=" + id));
        posts.answered(requestDto.getDocName(), requestDto.getAnswer());

        return id;
    }

    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다. id=" + id));

        return new PostResponseDto(entity);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }
}
