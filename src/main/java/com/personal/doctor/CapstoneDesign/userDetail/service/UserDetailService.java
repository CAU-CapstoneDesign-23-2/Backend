package com.personal.doctor.CapstoneDesign.userDetail.service;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.UserDetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.domain.UserDetails;
import com.personal.doctor.CapstoneDesign.userDetail.domain.UserDetailsRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailService {

    private final UsersRepository usersRepository;
    private final UserDetailsRepository userDetailsRepository;

    @Transactional
    public Long save(Long userId, UserDetailsSaveRequestDto requestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));
        requestDto.setUsers(users);

        UserDetails userDetails = requestDto.toEntity();
        userDetailsRepository.save(userDetails);

        return userDetails.getId();
    }

}
