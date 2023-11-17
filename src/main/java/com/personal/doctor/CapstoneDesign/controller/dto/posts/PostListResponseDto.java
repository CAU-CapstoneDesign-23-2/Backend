package com.personal.doctor.CapstoneDesign.controller.dto.posts;

import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import com.personal.doctor.CapstoneDesign.domain.users.Users;
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
    private Users user;

    public PostListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.question = entity.getQuestion();
        this.docName = entity.getDocName();
        this.answer = entity.getAnswer();
        this.user = entity.getUsers();
    }
}
