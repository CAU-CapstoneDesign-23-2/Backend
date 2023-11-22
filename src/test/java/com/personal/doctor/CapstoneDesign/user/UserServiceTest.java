package com.personal.doctor.CapstoneDesign.user;

import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @AfterEach
    public void clean() {
        userService.deleteAll();
    }

    @Test
    public void 사용자_회원가입() {
        UserJoinRequestDto requestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long userId = userService.join(requestDto);

        assertNotNull(userId);
    }

    @Test
    public void 사용자_로그인() {
        UserJoinRequestDto joinRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long joinUserId = userService.join(joinRequestDto);
        Long loginUserId = userService.login(joinRequestDto);

        assertEquals(joinUserId, loginUserId);
    }

    @Test
    public void 사용자_탈퇴() {
        UserJoinRequestDto joinRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long joinUserId = userService.join(joinRequestDto);

        Long deleteId = userService.delete(joinUserId);

        assertEquals(joinUserId, deleteId);
    }

    @Test
    public void 사용자_수정() {
        UserJoinRequestDto joinRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        Long joinUserId = userService.join(joinRequestDto);
        UserUpdateRequestDto updateRequestDto = UserUpdateRequestDto.builder()
                .userName("Name")
                .build();

        Long updateId = userService.update(joinUserId, updateRequestDto);

        assertEquals(joinUserId, updateId);
    }

}