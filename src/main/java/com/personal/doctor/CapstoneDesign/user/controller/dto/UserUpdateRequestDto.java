package com.personal.doctor.CapstoneDesign.user.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String userName;

    @Builder
    public UserUpdateRequestDto(String userName) {
        this.userName = userName;
    }
}
