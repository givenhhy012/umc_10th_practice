package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.FoodCategory;
import com.example.umc10th.domain.user.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class UserReqDTO {

    // 회원가입
    @Builder
    public record SignUp(
            List<Agree> agreeList,

            String name,
            Gender gender,
            LocalDate birth,
            String address,
            String detailAddress,
            List<FoodCategory> foodList,
            String email,
            String password
    ) {}

    // 회원가입 시 사용자가 약관에 동의했는지
    @Builder
    public record Agree (
            Long termId,
            boolean agreed
    ) {}

    @Builder
    public record Login(
            String email,
            String password
    ) {}
}
