package com.personal.doctor.CapstoneDesign.domain.users;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column
    private String userName;

    @Column
    private Integer userAge;

    @Builder
    public Users(String userID, String userPassword) {
        this.userID = userID;
        this.userPassword = userPassword;
        this.role = Role.USER;
    }

    public void updateRole() {
        this.role = Role.DOCTOR;
    }

    public void updateInfo(String userName, Integer userAge) {
        this.userName = userName;
        this.userAge = userAge;
    }
}
