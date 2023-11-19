package com.personal.doctor.CapstoneDesign.userDetail.controller.dto;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.userDetail.domain.UserDetails;
import lombok.Getter;

@Getter
public class UserDetailsResponseDto {

    private Long id;
    private String age;
    private String gender;
    private String disease1;
    private String disease2;
    private String disease3;
    private String surgery;
    private String activity1;
    private String activity2;
    private String activity3;
    private Users users;

    public UserDetailsResponseDto(UserDetails entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.gender = entity.getGender();
        this.disease1 = entity.getDisease1();
        this.disease2 = entity.getDisease2();
        this.disease3 = entity.getDisease3();
        this.surgery = entity.getSurgery();
        this.activity1 = entity.getActivity1();
        this.activity2 = entity.getActivity2();
        this.activity3 = entity.getActivity3();
        this.users = entity.getUsers();
    }

}
