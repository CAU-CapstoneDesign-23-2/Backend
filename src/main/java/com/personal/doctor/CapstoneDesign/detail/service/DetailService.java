package com.personal.doctor.CapstoneDesign.detail.service;

import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.detail.domain.Details;
import com.personal.doctor.CapstoneDesign.detail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DetailService {

    private final UsersRepository usersRepository;
    private final DetailsRepository detailsRepository;

    @Transactional
    public Long save(Long userId, DetailsSaveRequestDto requestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));
        requestDto.setUsers(users);

        Details details = requestDto.toEntity();
        detailsRepository.save(details);

        return details.getId();
    }

}
