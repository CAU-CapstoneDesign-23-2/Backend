package com.personal.doctor.CapstoneDesign.community.controller.dto;

import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostListResponseDto {
    private Long id;
    private String category;
    private String title;
    private String question;
    private String docName;
    private String answer;

    public PostListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.question = entity.getQuestion();
        this.docName = entity.getDocName();
        this.answer = entity.getAnswer();
    }
}
