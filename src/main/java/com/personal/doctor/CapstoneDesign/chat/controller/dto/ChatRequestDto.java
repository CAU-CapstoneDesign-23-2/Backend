package com.personal.doctor.CapstoneDesign.chat.controller.dto;

import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatRequestDto {

    private Long id;
    private String requestText;
    private String responseText;

    public ChatRequestDto(Chat entity) {
        id = entity.getId();
        requestText = entity.getRequestText();
        responseText = entity.getResponseText();
    }

}
