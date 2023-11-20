package com.personal.doctor.CapstoneDesign.community.service;

import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostAnsweredResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostListResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.community.domain.PostsRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;

    public PostService(UsersRepository usersRepository, PostsRepository postsRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(Long id, PostSaveRequestDto requestDto) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotExistException("존재하지 않는 사용자입니다."));
        requestDto.setUsers(user);
        Posts post = requestDto.toEntity();
        user.addPosts(post);
        post.setUser(user);
        postsRepository.save(post);

        return post.getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        posts.updatePosts(requestDto.getTitle(), requestDto.getQuestion());

        return id;
    }

    @Transactional
    public Long answered(Long id, PostAnsweredResponseDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        posts.answered(requestDto.getDocName(), requestDto.getAnswer());

        return id;
    }

    @Transactional
    public List<PostListResponseDto> search(String keyword) {
        return postsRepository.findPostsByKeyword(keyword).stream()
                .map(PostListResponseDto::new)
                .toList();
    }

    @Transactional
    public List<PostListResponseDto> findById(Long id) {
        return postsRepository.findAllUserPosts(id).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        postsRepository.deleteAll();
    }
}
