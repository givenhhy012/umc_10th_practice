package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.global.dto.PageResDTO;

public interface ReviewService {

    ReviewResDTO.Create createReview(Long userId, Long storeId, ReviewReqDTO.Create request);

    PageResDTO.PaginationWithCursor<ReviewResDTO.GetMyReviewItem> getMyReview(
            Integer pageSize,
            String cursor,
            String query,
            ReviewReqDTO.GetMyReview dto
    );
}
