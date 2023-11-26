package com.personal.doctor.CapstoneDesign.userDetail.controller;

import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsResponseDto;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.controller.dto.DetailsUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.userDetail.service.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DetailController {

    private final DetailsService detailsService;

    // 세부정보 저장
    @PostMapping(value = "/detail/{userId}", produces = "application/json;charset=UTF-8")
    public Long save(@PathVariable Long userId,
                     @RequestBody DetailsSaveRequestDto requestDto) {
        return detailsService.save(userId, requestDto);
    }

    // 세부정보 수정
    @PutMapping(value = "/detail/{userId}", produces = "application/json;charset=UTF-8")
    public Long update(@PathVariable Long userId,
                       @RequestBody DetailsUpdateRequestDto requestDto) {
        return detailsService.update(userId, requestDto);
    }

    // 세부정보 반환
    @GetMapping(value = "/detail/{userId}", produces = "application/json;charset=UTF-8")
    public DetailsResponseDto details(@PathVariable Long userId) {
        return detailsService.details(userId);
    }

}
