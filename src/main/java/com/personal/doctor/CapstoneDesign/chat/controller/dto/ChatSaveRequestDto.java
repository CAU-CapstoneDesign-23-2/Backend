package com.personal.doctor.CapstoneDesign.chat.controller.dto;

import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatSaveRequestDto {

    private Long type;
    private String content;
    private Users users;

    @Builder
    public ChatSaveRequestDto(Long type, String content) {
        this.type = type;
        this.content = content;
    }

    public Chat toEntity(ChatSaveRequestDto requestDto) {
        return Chat.builder()
                .type(requestDto.type)
                .content(requestDto.content)
                .build();
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
