package com.personal.doctor.CapstoneDesign.userDetail;

import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsResponseDto;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import com.personal.doctor.CapstoneDesign.userDetail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.userDetail.service.DetailsService;
import com.personal.doctor.CapstoneDesign.user.controller.dto.UserJoinRequestDto;
import com.personal.doctor.CapstoneDesign.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
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
        userService.deleteAll();
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

    @Test
    public void 세부사항_수정() {
        DetailsSaveRequestDto saveRequestDto = DetailsSaveRequestDto.builder()
                .age("65")
                .gender("Woman")
                .disease1("stomach")
                .hobby1("swimming")
                .build();
        Long saved = detailsService.save(userId, saveRequestDto);

        DetailsUpdateRequestDto updateRequestDto = DetailsUpdateRequestDto.builder()
                .age("66")
                .gender("Woman")
                .disease1(null)
                .hobby1("swimming")
                .hobby2("soccer")
                .build();
        Long updated = detailsService.update(userId, updateRequestDto);

        Details userDetails = detailsRepository.findUserDetails(userId);

        assertEquals(saved, updated);
        assertEquals("66", userDetails.getAge());
        assertNull(userDetails.getDisease1());
        assertEquals("soccer", userDetails.getHobby2());
    }

    @Test
    public void 세부사항_반환() {
        DetailsSaveRequestDto requestDto = DetailsSaveRequestDto.builder()
                .age("71")
                .gender("Man")
                .disease1("headache")
                .hobby1("golf")
                .medicine("cold")
                .build();
        Long detailID = detailsService.save(userId, requestDto);

        DetailsResponseDto responseDto = detailsService.details(userId);

        assertEquals("71", responseDto.getAge());
        assertEquals("Man", responseDto.getGender());
        assertEquals("headache", responseDto.getDisease1());
        assertEquals("golf", responseDto.getHobby1());
        assertEquals("cold", responseDto.getMedicine());
    }

}