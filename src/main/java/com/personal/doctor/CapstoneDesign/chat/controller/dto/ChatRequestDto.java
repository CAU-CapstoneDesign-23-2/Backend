package com.personal.doctor.CapstoneDesign.chat.controller.dto;

import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import lombok.Getter;

@Getter
public class ChatRequestDto {

    private Long id;
    private Long type;
    private String content;
    private Users users;

    public ChatRequestDto(Chat entity) {
        id = entity.getId();
        type = entity.getType();
        content = entity.getContent();
        users = entity.getUsers();
    }

}
