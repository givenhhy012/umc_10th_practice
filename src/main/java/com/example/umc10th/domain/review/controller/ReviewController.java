package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/stores/{stores-id}/reviews")
    public ApiResponse<ReviewResDTO.Create> createReview(
            @PathVariable("stores-id") Long storesId,
            @RequestParam Long userId,
            @RequestBody ReviewReqDTO.Create request
    ) {
        ReviewResDTO.Create result = reviewService.createReview(userId, storesId, request);
        BaseSuccessCode code = ReviewSuccessCode.CREATE_OK;
        return ApiResponse.onSuccess(code, result);
    }
}
