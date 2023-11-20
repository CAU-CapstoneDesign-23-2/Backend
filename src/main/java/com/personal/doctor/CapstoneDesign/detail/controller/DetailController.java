package com.personal.doctor.CapstoneDesign.detail.controller;

import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsSaveRequestDto;
import com.personal.doctor.CapstoneDesign.detail.controller.dto.DetailsUpdateRequestDto;
import com.personal.doctor.CapstoneDesign.detail.service.DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class DetailController {

    private final DetailsService detailsService;

    // 세부정보 저장
    @PostMapping("/detail/{userId}")
    public Long save(@PathVariable Long userId,
                     @RequestBody DetailsSaveRequestDto requestDto) {
        return detailsService.save(userId, requestDto);
    }

    // 세부정보 수정
    @PutMapping("/detail/{userId}")
    public Long update(@PathVariable Long userId,
                       @RequestBody DetailsUpdateRequestDto requestDto) {
        return detailsService.update(userId, requestDto);
    }

}
