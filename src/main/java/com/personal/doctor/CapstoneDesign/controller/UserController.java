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

    @PostMapping("/join")
    public Long join(@RequestBody UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }

    @PostMapping("/login")
    public Long login(@RequestBody UserJoinRequestDto requestDto) {
        return userService.login(requestDto);
    }

    @PutMapping("/user/{id}")
    public Long updateUserInfo(@PathVariable Long id,
                               @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @PutMapping("/user/role/{id}")
    public Long updateUserRole(@PathVariable Long id) {
        return userService.updateRole(id);
    }

    // TODO: UserDetails 저장 기능
    // TODO: id -> stdId 변경
    // TODO: 주석 달기

}
