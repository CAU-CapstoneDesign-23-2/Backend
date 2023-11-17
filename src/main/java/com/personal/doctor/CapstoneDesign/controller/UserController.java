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

    // 사용자 탈퇴
    @DeleteMapping("/delete/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    // 사용자 정보 수정
    @PutMapping("/user/{stuId}")
    public Long updateUserInfo(@PathVariable Long stuId,
                               @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(stuId, requestDto);
    }

    // 사용자 역할 수정
    @PutMapping("/user/role/{stuId}")
    public Long updateUserRole(@PathVariable Long stuId) {
        return userService.updateRole(stuId);
    }

    // TODO: UserDetails 저장 기능

}
