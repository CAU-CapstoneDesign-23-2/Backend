package com.personal.doctor.CapstoneDesign.userDetail;

import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import com.personal.doctor.CapstoneDesign.userDetail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
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
        UserJoinRequestDto users = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userID = userService.join(users);
    }

    @AfterEach
    public void clean() {
        detailsRepository.deleteAll();
        usersRepository.deleteAll();
        userService.deleteAll();
    }

    @Test
    public void 세부정보_저장() {

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