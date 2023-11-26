package com.personal.doctor.CapstoneDesign.hospital.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;        // 병원 이름

    @Column
    private String code;        // 종별코드명 (Ex. 상급종합, 정신병원, 요양병원, 의원, 보건지소, 보건진료소, 치과병원, 치과의원, 한방병원, 한의원)

    @Column
    private String city;        // 시도

    @Column
    private String district;    // 시군구

    @Column
    private String town;        // 읍면동

    @Column
    private String postalCode;  // 우편번호

    @Column
    private String address;     // 주소

    @Column
    private String telephone;   // 전화번호

    @Builder
    public Hospital(String name, String code, String city, String district, String town, String postalCode, String address, String telephone) {
        this.name = name;
        this.code = code;
        this.city = city;
        this.district = district;
        this.town = town;
        this.postalCode = postalCode;
        this.address = address;
        this.telephone = telephone;
    }

}
