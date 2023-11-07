package com.personal.doctor.CapstoneDesign.controller.dto;

import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {
    private String category;
    private String title;
    private String question;

    @Builder
    public PostSaveRequestDto(String category, String title, String question) {
        this.category = category;
        this.title = title;
        this.question = question;
    }

    public Posts toEntity() {
        return Posts.builder()
                .category(category)
                .title(title)
                .question(question)
                .build();
    }

}
