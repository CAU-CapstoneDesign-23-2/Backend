package com.personal.doctor.CapstoneDesign.detail.domain;

import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DetailsRepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    private static Long userID;

    @BeforeEach
    public void setup() {
        detailsRepository.deleteAll();
        usersRepository.deleteAll();
        userService.deleteAll();

        UserJoinRequestDto users = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userID = userService.join(users);
    }

    @Test
    public void 사용자_세부정보_저장() {

        Users user = usersRepository.findById(userID)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        Details details = Details.builder()
                .age("age")
                .gender("gender")
                .users(user)
                .build();
        user.updateDetails(details);
        detailsRepository.save(details);

        Assertions.assertThat(user.getDetails().getAge().equals("age"));
    }

}