package com.example.umc10th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDate;

public class UserReqDTO {

    @Builder
    public record SignUp(
            String email,
            String password,
            String name,
            LocalDate birthDate,
            String gender,
            String address,
            String detailAddress
    ) {}

    @Builder
    public record Login(
            String email,
            String password
    ) {}
}
