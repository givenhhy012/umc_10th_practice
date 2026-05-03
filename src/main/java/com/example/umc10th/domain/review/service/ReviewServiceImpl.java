package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public ReviewResDTO.Create createReview(Long userId, Long storeId, ReviewReqDTO.Create request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.STORE_NOT_FOUND));

        Review review = ReviewConverter.toEntity(user, store, request);
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateResponse(saved);
    }
}
