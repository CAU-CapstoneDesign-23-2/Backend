package com.personal.doctor.CapstoneDesign.hospital.service;

import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalListResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.domain.HospitalRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    private final UsersRepository usersRepository;
    private final HospitalRepository hospitalRepository;

    public HospitalService(UsersRepository usersRepository, HospitalRepository hospitalRepository) {
        this.usersRepository = usersRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public List<HospitalListResponseDto> findHospitalType(Long userID, String type) {
        Users users = usersRepository.findById(userID)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        return hospitalRepository.findHospitalsByName(users.getLocation(), type).stream()
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
