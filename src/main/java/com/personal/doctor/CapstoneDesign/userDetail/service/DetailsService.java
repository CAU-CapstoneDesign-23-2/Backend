package com.personal.doctor.CapstoneDesign.userDetail.service;

import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsResponseDto;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import com.personal.doctor.CapstoneDesign.userDetail.domain.DetailsRepository;
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
        users.updateDetails(details);

        return details.getId();
    }

    @Transactional
    public Long update(Long userId, DetailsUpdateRequestDto requestDto) {

        Details userDetails = detailsRepository.findUserDetails(userId);
        userDetails.setAge(requestDto.getAge());
        userDetails.setGender(requestDto.getGender());
        userDetails.setDisease1(requestDto.getDisease1());
        userDetails.setDisease2(requestDto.getDisease2());
        userDetails.setDisease3(requestDto.getDisease3());
        userDetails.setSurgery(requestDto.getSurgery());
        userDetails.setHobby1(requestDto.getHobby1());
        userDetails.setHobby2(requestDto.getHobby2());
        userDetails.setHobby3(requestDto.getHobby3());
        userDetails.setMedicine(requestDto.getMedicine());

        return userDetails.getId();
    }

    @Transactional
    public DetailsResponseDto details(Long userId) {
        Details details = detailsRepository.findUserDetails(userId);
        return new DetailsResponseDto(details);
    }

    @Transactional
    public void deleteAll() {
        detailsRepository.deleteAll();
    }

}
