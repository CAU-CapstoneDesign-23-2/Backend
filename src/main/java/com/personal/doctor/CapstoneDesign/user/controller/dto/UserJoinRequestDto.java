package com.personal.doctor.CapstoneDesign.user.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private String userID;
    private String userPassword;
    private String userName;
    private String location;

    @Builder
    public UserJoinRequestDto(String userID, String userPassword, String userName, String location) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.userName = userName;
        this.location = location;
    }

}
