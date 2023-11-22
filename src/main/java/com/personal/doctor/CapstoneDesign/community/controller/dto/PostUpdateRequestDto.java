package com.personal.doctor.CapstoneDesign.community.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateRequestDto {
    private String title;
    private String question;

    @Builder
    public PostUpdateRequestDto(String title, String question) {
        this.title = title;
        this.question = question;
    }
}
