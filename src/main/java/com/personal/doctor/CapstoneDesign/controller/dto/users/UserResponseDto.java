package com.personal.doctor.CapstoneDesign.controller.dto.users;

import com.personal.doctor.CapstoneDesign.domain.users.Role;
import com.personal.doctor.CapstoneDesign.domain.users.Users;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String userID;
    private String userPassword;
    private Role role;
    private String userName;
    private Integer userAge;

    public UserResponseDto(Users entity) {
        this.id = entity.getId();
        this.userID = entity.getUserID();
        this.userPassword = entity.getUserPassword();
        this.role = entity.getRole();
        this.userName = entity.getUserName();
        this.userAge = entity.getUserAge();
    }
}
