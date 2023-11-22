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

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    @Query("SELECT p FROM Posts p WHERE (p.title LIKE %:keyword%) OR (p.question LIKE %:keyword%) ORDER BY p.id DESC")
    List<Posts> findPostsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT p FROM Posts p WHERE p.users.id = :id ORDER BY p.id DESC ")
    List<Posts> findAllUserPosts(@Param("id") Long userId);
}
