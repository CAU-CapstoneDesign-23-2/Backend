package com.personal.doctor.CapstoneDesign.user.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginRequestDto {

    private String userID;
    private String userPassword;

    @Builder
    public UserLoginRequestDto(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }

}
