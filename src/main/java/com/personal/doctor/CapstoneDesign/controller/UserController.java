package com.personal.doctor.CapstoneDesign.controller;

import com.personal.doctor.CapstoneDesign.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.controller.dto.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Long joinUser(@RequestBody UserJoinRequestDto requestDto) {
        return userService.join(requestDto);
    }

    @PutMapping("/user/{id}")
    public Long updateUserInfo(@PathVariable Long id,
                               @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    // TODO: UserDetails 저장 기능

}
