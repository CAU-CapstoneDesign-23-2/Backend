package com.personal.doctor.CapstoneDesign.detail.service;

import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsUpdateRequestDto;
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
public class DetailsService {

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

    @Transactional
    public Long update(Long userId, DetailsUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        Details details = users.getDetails();
        details.setAge(requestDto.getAge());
        details.setGender(requestDto.getGender());
        details.setDisease1(requestDto.getDisease1());
        details.setDisease2(requestDto.getDisease2());
        details.setDisease3(requestDto.getDisease3());
        details.setSurgery(requestDto.getSurgery());
        details.setActivity1(requestDto.getActivity1());
        details.setActivity2(requestDto.getActivity2());
        details.setActivity3(requestDto.getActivity3());

        return details.getId();
    }

}
