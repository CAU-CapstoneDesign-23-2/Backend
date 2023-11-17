package com.personal.doctor.CapstoneDesign.controller;

import com.personal.doctor.CapstoneDesign.controller.dto.users.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.users.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.service.UserService;
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
    @PostMapping("/login")
    public Long login(@RequestBody UserJoinRequestDto requestDto) {
        return userService.login(requestDto);
    }

    // 사용자 정보 수정
    @PutMapping("/user/{id}")
    public Long updateUserInfo(@PathVariable Long id,
                               @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    // 사용자 역할 수정
    @PutMapping("/user/role/{id}")
    public Long updateUserRole(@PathVariable Long id) {
        return userService.updateRole(id);
    }

    // TODO: UserDetails 저장 기능

}
