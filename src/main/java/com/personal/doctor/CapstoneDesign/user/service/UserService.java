package com.personal.doctor.CapstoneDesign.user.service;

import com.personal.doctor.CapstoneDesign.util.exceptions.UserAlreadyExistException;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserLoginFailureException;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    // 사용자 회원가입
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

    // 사용자 로그인
    @Transactional
    public Long login(UserJoinRequestDto requestDto) {
        Optional<Users> users = usersRepository.findByUserID(requestDto.getUserID());
        if (users.isEmpty()) {
            throw new UserLoginFailureException("로그인 실패");
        } else {
            if (!users.get().getUserPassword().equals(requestDto.getUserPassword())) {
                throw new UserLoginFailureException("로그인 실패");
            }
        }
        return users.get().getId();
    }

    // 사용자 탈퇴
    @Transactional
    public Long delete(Long userId) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다"));
        users.beforeDelete();
        usersRepository.delete(users);

        return users.getId();
    }

    // 중복 ID 검사
    public void findDuplicateUser(String userID) {
        Optional<Users> user = usersRepository.findByUserID(userID);
        if(user.isPresent()) {
            throw new UserAlreadyExistException("이미 "+userID+"를 사용하는 사용자가 존재합니다");
        }
    }

    // 사용자 이름 수정
    @Transactional
    public Long update(Long userId, UserUpdateRequestDto requestDto) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다. id=" + userId));
        user.updateUserName(requestDto.getUserName());

        return userId;
    }

    // 사용자 역할 변경 USER --> DOCTOR
    @Transactional
    public Long updateRole(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotExistException("존재하지 않는 사용자입니다."));
        return users.updateRole();
    }

    @Transactional
    public void deleteAll() {
        usersRepository.deleteAll();
    }

}
