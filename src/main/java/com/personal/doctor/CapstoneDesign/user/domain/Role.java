package com.personal.doctor.CapstoneDesign.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER", "사용자"),
    DOCTOR("ROLE_DOCTOR", "의사");

    private final String key;
    private final String title;
}
