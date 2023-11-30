package com.personal.doctor.CapstoneDesign.user.controller.dto;

import com.personal.doctor.CapstoneDesign.user.domain.Role;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String userID;
    private String userPassword;
    private Role role;
    private String userName;
    private String location;
    private Details details;

    public UserResponseDto(Users entity) {
        this.id = entity.getId();
        this.userID = entity.getUserID();
        this.userPassword = entity.getUserPassword();
        this.role = entity.getRole();
        this.userName = entity.getUserName();
        this.location = entity.getLocation();
        this.details = entity.getDetails();
    }
}
