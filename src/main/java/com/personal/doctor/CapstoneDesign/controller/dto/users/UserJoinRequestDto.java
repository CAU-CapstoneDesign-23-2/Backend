package com.personal.doctor.CapstoneDesign.controller.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private String userID;
    private String userPassword;

    @Builder
    public UserJoinRequestDto(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
    }

}
