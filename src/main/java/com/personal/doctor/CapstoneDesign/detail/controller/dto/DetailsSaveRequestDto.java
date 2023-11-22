package com.personal.doctor.CapstoneDesign.detail.controller.dto;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.detail.domain.Details;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DetailsSaveRequestDto {

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

    public DetailsSaveRequestDto(String age, String gender, String disease1, String disease2, String disease3,
                                 String surgery, String hobby1, String hobby2, String hobby3, String medicine) {
        this.age = age;
        this.gender = gender;
        this.disease1 = disease1;
        this.disease2 = disease2;
        this.disease3 = disease3;
        this.surgery = surgery;
        this.hobby1 = hobby1;
        this.hobby2 = hobby2;
        this.hobby3 = hobby3;
        this.medicine = medicine;
    }

    public Details toEntity() {
        return Details.builder()
                .age(age)
                .gender(gender)
                .disease1(disease1)
                .disease2(disease2)
                .disease3(disease3)
                .surgery(surgery)
                .hobby1(hobby1)
                .hobby2(hobby2)
                .hobby3(hobby3)
                .medicine(medicine)
                .users(users)
                .build();
    }

    public void setUsers(Users users) {
        this.users = users;
    }

}