package com.personal.doctor.CapstoneDesign.hospital.controller;

import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalListResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    // 이름에 있는 병원 종류로 병원 검색 Ex. ~한의원, ~내과, ~소아과
    @GetMapping(value = "/hospital/name", produces = "application/json;charset=UTF-8")
    public List<HospitalListResponseDto> withName(@RequestParam("type") String type) {
        return hospitalService.findHospitalType(type);
    }

    // 사용자가 입력한 주소에 있는 병원들 반환
    @GetMapping(value = "/hospital/address", produces = "application/json;charset=UTF-8")
    public List<HospitalListResponseDto> withAddress(@RequestBody HospitalResponseDto responseDto) {
        return hospitalService.findHospitalAddress(responseDto);
    }

}
