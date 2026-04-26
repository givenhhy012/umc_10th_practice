package com.example.umc10th.domain.review.dto;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record Create(
            Integer rate,
            String reviewBody
    ) {}
}
