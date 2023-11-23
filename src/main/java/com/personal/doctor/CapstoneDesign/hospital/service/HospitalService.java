package com.personal.doctor.CapstoneDesign.hospital.service;

import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalListResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.domain.HospitalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public List<HospitalListResponseDto> findHospitalType(String type) {
        return hospitalRepository.findHospitalsByName(type).stream()
                .map(HospitalListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<HospitalListResponseDto> findHospitalAddress(HospitalResponseDto responseDto) {
        return hospitalRepository.findHospitalByAddress(responseDto.getCity(), responseDto.getDistrict(), responseDto.getTown()).stream()
                .map(HospitalListResponseDto::new)
                .collect(Collectors.toList());
    }

}
