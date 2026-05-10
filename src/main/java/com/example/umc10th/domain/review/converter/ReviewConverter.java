package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.user.entity.User;

public class ReviewConverter {

    public static Review toEntity(User user, Store store, ReviewReqDTO.Create request) {
        return Review.builder()
                .user(user)
                .store(store)
                .rate(request.rate())
                .reviewBody(request.reviewBody())
                .build();
    }

    public static ReviewResDTO.Create toCreateResponse(Review review) {
        return ReviewResDTO.Create.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 내 리뷰 조회
    public static ReviewResDTO.GetMyReviewItem toGetMyReviewItem(Review review) {
        User user = review.getUser();
        Store store = review.getStore();

        String reply = review.getReply() != null
                ? review.getReply().getReplyBody()
                : null;


        return ReviewResDTO.GetMyReviewItem.builder()
                .reviewId(review.getId())
                .reviewBody(review.getReviewBody())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .userNickName(user.getNickName())
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .reply(reply)
                .build();
    }

    //
}
