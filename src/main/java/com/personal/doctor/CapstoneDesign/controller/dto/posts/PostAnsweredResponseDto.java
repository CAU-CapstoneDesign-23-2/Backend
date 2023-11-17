package com.personal.doctor.CapstoneDesign.controller.dto.posts;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostAnsweredResponseDto {
    private String docName;
    private String answer;

    @Builder
    public PostAnsweredResponseDto(String docName, String answer) {
        this.docName = docName;
        this.answer = answer;
    }

}
