package com.personal.doctor.CapstoneDesign.community.service;

import com.personal.doctor.CapstoneDesign.user.service.UserService;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostAnsweredResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostListResponseDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostSaveRequestDto;
import com.personal.doctor.CapstoneDesign.community.controller.dto.PostUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.community.domain.PostsRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotQualifiedException;
import org.springframework.cglib.core.SpringNamingPolicy;
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

    // Post 저장
    @Transactional
    public Long save(Long userId, PostSaveRequestDto requestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("존재하지 않는 사용자입니다."));

        Posts post = requestDto.toEntity();
        post.setUsers(users);
        users.addPosts(post);
        postsRepository.save(post);

        return post.getId();
    }

    // Post 수정
    @Transactional
    public Long update(Long postId, PostUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        posts.updatePosts(requestDto.getTitle(), requestDto.getQuestion());

        return postId;
    }

    // Post 답변
    @Transactional
    public Long answered(Long userId, Long postId, PostAnsweredResponseDto requestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("존재하지 않는 사용자입니다."));
        Posts posts = postsRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        if (users.getRole().toString().trim().equals("DOCTOR")) {
            posts.answered(requestDto.getDocName(), requestDto.getAnswer());
            return postId;
        } else {
            throw new UserNotQualifiedException("권한이 없습니다.");
        }
    }

    // Post 검색
    @Transactional
    public List<PostListResponseDto> search(String keyword) {
        return postsRepository.findPostsByKeyword(keyword).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 사용자의 Post 반환
    @Transactional
    public List<PostListResponseDto> findById(Long userId) {
        return postsRepository.findAllUserPosts(userId).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 모든 Post 반환
    @Transactional(readOnly = true)
    public List<PostListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 카테고리별 Post 반환
    @Transactional
    public List<PostListResponseDto> findByCategory(String category) {
        return postsRepository.findPostsByCategory(category).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 카테고리에서의 검색
    @Transactional
    public List<PostListResponseDto> findCategoryByKeyword(String category, String keyword) {
        return postsRepository.findPostsCategoryByKeyword(category, keyword).stream()
                .map(PostListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAll() {
        postsRepository.deleteAll();
    }
}
