package com.personal.doctor.CapstoneDesign.hospital.controller;

import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalListResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.service.HospitalService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // 이름에 있는 병원 종류로 병원 검색 Ex. ~한의원, ~내과, ~소아과
    @GetMapping(value = "/hospital/name/{userID}", produces = "application/json;charset=UTF-8")
    public List<HospitalListResponseDto> withName(@PathVariable Long userID,
                                                  @RequestParam("type") String type) {
        System.out.println("Hospital Search Controller is Accessed.");
        return hospitalService.findHospitalType(userID, type);
    }

    // 사용자가 입력한 주소에 있는 병원들 반환
    @GetMapping(value = "/hospital/address/{userID}", produces = "application/json;charset=UTF-8")
    public List<HospitalListResponseDto> withAddress(@PathVariable Long userID) {
        System.out.println("Hospital Return Controller is Accessed.");
        return hospitalService.findHospitalAddress(userID);
    }

    // 메인 화면에 노출할 사용자 거주지 주변 병원 한 개 반환
    @Cacheable(value = "userHospital", key = "#userID")
    @GetMapping(value = "/hospital/main/{userID}", produces = "application/json;charset=UTF-8")
    public HospitalListResponseDto onMain(@PathVariable Long userID) {
        System.out.println("Hospital for Main Page Controller is Accessed.");
        return hospitalService.findOneHospitalByAddress(userID);
    }

}
