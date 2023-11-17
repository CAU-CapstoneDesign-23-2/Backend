package com.personal.doctor.CapstoneDesign.controller.dto.posts;

import com.personal.doctor.CapstoneDesign.domain.posts.Posts;
import com.personal.doctor.CapstoneDesign.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String category;
    private String title;
    private String question;
    private Users users;

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
                .users(users)
                .build();
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
