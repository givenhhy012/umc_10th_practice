package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.dto.PageResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reviews/my")
    public ApiResponse<PageResDTO.PaginationWithCursor<ReviewResDTO.GetMyReviewItem>> getMyReview(
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String cursor,
            @RequestParam String query,
            @RequestBody ReviewReqDTO.GetMyReview dto
    ) {
        PageResDTO.PaginationWithCursor<ReviewResDTO.GetMyReviewItem> result = reviewService.getMyReview(pageSize, cursor, query, dto);
        BaseSuccessCode code = ReviewSuccessCode.MY_REVIEW_OK;
        return ApiResponse.onSuccess(code, result);
    }
}
