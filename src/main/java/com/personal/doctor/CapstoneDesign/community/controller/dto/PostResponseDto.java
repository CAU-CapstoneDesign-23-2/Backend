package com.personal.doctor.CapstoneDesign.community.controller.dto;

import com.personal.doctor.CapstoneDesign.community.domain.Posts;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String category;
    private String title;
    private String question;
    private String docName;
    private String answer;
    private Users user;

    public PostResponseDto(Posts entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.question = entity.getQuestion();
        this.docName = entity.getDocName();
        this.answer = entity.getAnswer();
        this.user = entity.getUsers();
    }

}
