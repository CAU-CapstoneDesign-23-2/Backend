package com.personal.doctor.CapstoneDesign.hospital.controller.dto;

import com.personal.doctor.CapstoneDesign.hospital.domain.Hospital;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HospitalListResponseDto {

    private Long id;
    private String name;
    private String code;
    private String city;
    private String district;
    private String town;
    private String postalCode;
    private String address;
    private String telephone;

    public HospitalListResponseDto(Hospital entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.town = entity.getTown();
        this.postalCode = entity.getPostalCode();
        this.address = entity.getAddress();
        this.telephone = entity.getTelephone();
    }

}
