package com.personal.doctor.CapstoneDesign.controller.dto;

import lombok.Builder;
import lombok.Getter;

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
