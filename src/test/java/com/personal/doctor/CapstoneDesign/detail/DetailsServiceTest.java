package com.personal.doctor.CapstoneDesign.detail;

import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.detail.domain.Details;
import com.personal.doctor.CapstoneDesign.detail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.detail.service.DetailsService;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DetailsServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private DetailsRepository detailsRepository;

    private static Long userId;

    @BeforeEach
    public void setup() {
        UserJoinRequestDto doctorRequestDto = UserJoinRequestDto.builder()
                .userID("ID")
                .userPassword("PW")
                .build();
        userId = userService.join(doctorRequestDto);
    }

    @AfterEach
    public void clean() {
        detailsService.deleteAll();
    }

    @Test
    public void 세부사항_저장() {
        DetailsSaveRequestDto requestDto = DetailsSaveRequestDto.builder()
                .age("71")
                .gender("Man")
                .disease1("headache")
                .hobby1("golf")
                .medicine("cold")
                .build();
        Long detailID = detailsService.save(userId, requestDto);
        Details details = detailsRepository.findById(detailID).get();

        assertEquals("Man", details.getGender());
    }

}