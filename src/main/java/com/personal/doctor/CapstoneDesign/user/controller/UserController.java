package com.personal.doctor.CapstoneDesign.user.controller;

import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserLoginRequestDto;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserResponseDto;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 사용자 회원 가입
    @PostMapping("/join")
    public Long join(@RequestBody UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }

    // 사용자 로그인
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public UserResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return userService.login(requestDto);
    }

    // 관리자 로그인
    @PostMapping(value = "/admin", produces = "application/json;charset=UTF-8")
    public UserResponseDto adminLogin(@RequestBody UserLoginRequestDto requestDto) {
        return userService.adminLogin(requestDto);
    }

    // 사용자 탈퇴
    @DeleteMapping("/user/{userId}")
    public Long delete(@PathVariable Long userId) {
        return userService.delete(userId);
    }

    // 사용자 정보 수정
    @PutMapping("/user/{userId}")
    public Long updateUserName(@PathVariable Long userId,
                               @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(userId, requestDto);
    }

    // 사용자 역할 수정
    @PutMapping("/user/role/{userId}")
    public Long updateUserRole(@PathVariable Long userId) {
        return userService.updateRole(userId);
    }

}
