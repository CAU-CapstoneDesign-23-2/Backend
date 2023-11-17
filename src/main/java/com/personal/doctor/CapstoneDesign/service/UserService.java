package com.personal.doctor.CapstoneDesign.service;

import com.personal.doctor.CapstoneDesign.util.UserAlreadyExistException;
import com.personal.doctor.CapstoneDesign.util.UserLoginFailureException;
import com.personal.doctor.CapstoneDesign.util.UserNotExistException;
import com.personal.doctor.CapstoneDesign.controller.dto.users.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.users.UserUpdateRequestDto;
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

    @Transactional
    public Long delete(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다"));
        users.beforeDelete();
        usersRepository.delete(users);

        return users.getId();
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

    @Transactional
    public Long updateRole(Long id) {
        Users users = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotExistException("존재하지 않는 사용자입니다."));
        return users.updateRole();
    }

    // TODO 1: 사용자의 간단한 정보 저장하는 기능 추가
    // TODO 2: 사용자의 세부 정보 저장하는 기능 추가

}
