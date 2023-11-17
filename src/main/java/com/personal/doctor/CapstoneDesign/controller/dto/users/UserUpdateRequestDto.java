package com.personal.doctor.CapstoneDesign.controller.dto.users;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String userName;
    private Integer userAge;

    @Builder
    public UserUpdateRequestDto(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }
}
