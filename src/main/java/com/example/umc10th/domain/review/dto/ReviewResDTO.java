package com.example.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰 작성
    @Builder
    public record Create(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 내가 작성한 리뷰 조회
    @Builder
    public record GetMyReviewItem(
            Long reviewId,
            String reviewBody,
            Float rate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,

            String userNickName,

            Long storeId,
            String storeName,

            String reply
    ) {}

    @Builder
    public record GetMyReviewList(
            Long totalReviewCount,
            List<GetMyReviewItem> reviews
    ) {}

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "review_body", nullable = false)
//    private String reviewBody;
//
//    @Column(name = "rate", nullable = false)
//    private Integer rate;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "store_id")
//    private Store store;
}
