package com.personal.doctor.CapstoneDesign.service;

import com.personal.doctor.CapstoneDesign.common.UserAlreadyExistException;
import com.personal.doctor.CapstoneDesign.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.domain.users.Users;
import com.personal.doctor.CapstoneDesign.domain.users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public Long join(UserJoinRequestDto requestDto) {
        findDuplicateUser(requestDto.getUserID());
        Users user = Users.builder()
                .userID(requestDto.getUserID())
                .userPassword(requestDto.getUserPassword())
                .build();
        usersRepository.save(user);
        return user.getId();
    }

    public void findDuplicateUser(String userID) {
        Optional<Users> user = usersRepository.findByUserID(userID);
        if(user.isPresent()) {
            throw new UserAlreadyExistException("이미 "+userID+"를 사용하는 사용자가 존재합니다");
        }
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. id=" + id));
        user.updateInfo(requestDto.getUserName(), requestDto.getUserAge());

        return id;
    }

    // TODO 1: 사용자의 간단한 정보 저장하는 기능 추가
    // TODO 2: 사용자의 세부 정보 저장하는 기능 추가

}
