package com.personal.doctor.CapstoneDesign.userDetail.controller.dto;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DetailsResponseDto {

    private Long id;
    private String age;
    private String gender;
    private String disease1;
    private String disease2;
    private String disease3;
    private String surgery;
    private String hobby1;
    private String hobby2;
    private String hobby3;
    private String medicine;
    private Users users;

    public DetailsResponseDto(Details entity) {
        this.id = entity.getId();
        this.age = entity.getAge();
        this.gender = entity.getGender();
        this.disease1 = entity.getDisease1();
        this.disease2 = entity.getDisease2();
        this.disease3 = entity.getDisease3();
        this.surgery = entity.getSurgery();
        this.hobby1 = entity.getHobby1();
        this.hobby2 = entity.getHobby2();
        this.hobby3 = entity.getHobby3();
        this.medicine = entity.getMedicine();
        this.users = entity.getUsers();
    }

}
