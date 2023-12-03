package com.personal.doctor.CapstoneDesign.community.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    Optional<Posts> findById(Long id);

    // 모든 게시물 반환
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    // 게시물의 제목과 내용으로 검색
    @Query("SELECT p FROM Posts p WHERE (p.title LIKE %:keyword%) OR (p.question LIKE %:keyword%) ORDER BY p.id DESC")
    List<Posts> findPostsByKeyword(@Param("keyword") String keyword);

    // 특정 사용자의 게시물들 반환
    @Query("SELECT p FROM Posts p WHERE p.users.id = :id ORDER BY p.id DESC ")
    List<Posts> findAllUserPosts(@Param("id") Long userId);

    // 카테고리별 게시물 반환
    @Query("SELECT p FROM Posts p WHERE p.category = :category ORDER BY p.id DESC")
    List<Posts> findPostsByCategory(@Param("category") String category);

    // 카테고리별 게시물에서의 검색
    @Query("SELECT p FROM Posts p WHERE (p.category = :category) AND ((p.title LIKE %:keyword%) OR (p.question LIKE %:keyword%)) ORDER BY p.id DESC")
    List<Posts> findPostsCategoryByKeyword(@Param("category") String category,
                                           @Param("keyword") String keyword);
}
