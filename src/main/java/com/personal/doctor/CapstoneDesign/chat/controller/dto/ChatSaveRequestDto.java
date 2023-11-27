package com.personal.doctor.CapstoneDesign.chat.controller.dto;

import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatSaveRequestDto {

    private String requestText;
    private String responseText;
    private Users users;

    @Builder
    public ChatSaveRequestDto(String requestText, String responseText) {
        this.requestText = requestText;
        this.responseText = responseText;
    }

    public Chat toEntity() {
        return Chat.builder()
                .requestText(requestText)
                .responseText(responseText)
                .users(users)
                .build();
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}
