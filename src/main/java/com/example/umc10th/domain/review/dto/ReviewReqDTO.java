package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record Create(
            Float rate,
            String reviewBody
    ) {}

    // 내가 작성한 리뷰 조회용
    @Builder
    public record GetMyReview(
            Long userId
    ) {}
}
