package com.example.umc10th.domain.user.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class UserResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point

    ){}

    @Builder
    public record SignUp(
            Long userId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record Login(
            Long userId,
            String name,
            String accessToken
    ) {}

    @Builder
    public record LoginResult(
            String accessToken
    ) {}
}
