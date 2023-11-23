package com.personal.doctor.CapstoneDesign.hospital.controller.dto;

import com.personal.doctor.CapstoneDesign.hospital.domain.Hospital;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HospitalResponseDto {

    private String city;
    private String district;
    private String town;

    @Builder
    public HospitalResponseDto(String city, String district, String town) {
        this.city = city;
        this.district = district;
        this.town = town;
    }

}