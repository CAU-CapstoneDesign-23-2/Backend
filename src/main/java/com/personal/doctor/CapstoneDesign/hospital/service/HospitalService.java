package com.personal.doctor.CapstoneDesign.hospital.service;

import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalListResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.controller.dto.HospitalResponseDto;
import com.personal.doctor.CapstoneDesign.hospital.domain.Hospital;
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

    // 사용자 거주지의 병원 중 병원 type으로 검색한 결과를 List로 반환
    @Transactional
    public List<HospitalListResponseDto> findHospitalType(Long userID, String type) {
        Users users = usersRepository.findById(userID)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        return hospitalRepository.findHospitalsByName(users.getLocation().trim(), type).stream()
                .map(HospitalListResponseDto::new)
                .toList();
    }

    // 사용자 거주지의 병원 List로 반환
    @Transactional
    public List<HospitalListResponseDto> findHospitalAddress(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        return hospitalRepository.findHospitalByAddress(users.getLocation()).stream()
                .map(HospitalListResponseDto::new)
                .toList();
    }

    // 사용자 거주지 병원 중 메인 화면에 노출할 한 개의 병원 노출
    @Transactional
    public HospitalListResponseDto findOneHospitalByAddress(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        return new HospitalListResponseDto(hospitalRepository.findHospitalsByAddress(users.getLocation()).get(0));
    }


}
