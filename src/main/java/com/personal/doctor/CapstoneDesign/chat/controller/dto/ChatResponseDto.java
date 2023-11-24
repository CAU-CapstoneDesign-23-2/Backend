package com.personal.doctor.CapstoneDesign.chat.controller.dto;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatResponseDto {

    private Long type;
    private String content;
    private Users users;

    @Builder
    public ChatResponseDto(Long type, String content, Users users) {
        this.type = type;
        this.content = content;
        this.users = users;
    }

}
