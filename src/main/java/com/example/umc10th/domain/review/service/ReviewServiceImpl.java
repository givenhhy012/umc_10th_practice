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
import com.example.umc10th.global.converter.PageConverter;
import com.example.umc10th.global.dto.PageResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 리뷰 생성
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

    // 내가 생성한 리뷰 전체 조회
    @Override
    public PageResDTO.PaginationWithCursor<ReviewResDTO.GetMyReviewItem> getMyReview(
            Integer pageSize,
            String cursor,
            String query,
            ReviewReqDTO.GetMyReview dto
    ) {
        // 유저 찾기
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND));

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;
        boolean isFirstPage = cursor == null || cursor.equals("-1");



        switch (query.toLowerCase()){
            case "id":
                // 커서가 없는 경우
                if (isFirstPage) {
                    reviewList = reviewRepository.findReviewByUser_IdOrderByIdDesc(dto.userId(), pageRequest);
                }
                // 있는 경우
                else {
                    // 커서 분리
                    String[] parts = cursor.split(":");
                    idCursor = Long.parseLong(parts[0]);
                    reviewList = reviewRepository.findReviewByUser_IdAndIdLessThanOrderByIdDesc(
                            dto.userId(), idCursor, pageRequest);
                }
                break;
            case "rate":
                if (isFirstPage) {
                    reviewList = reviewRepository.findReviewByUser_IdOrderByRateDescIdDesc(dto.userId(), pageRequest);
                } else {
                    // 커서 분리
                    String[] parts = cursor.split(":");
                    Float rateCursor = Float.parseFloat(parts[0]);
                    idCursor = Long.parseLong(parts[1]);
                    reviewList = reviewRepository.findByUserIdWithRateCursor(
                            dto.userId(), rateCursor, idCursor, pageRequest);
                }
                break;
            default:
                throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
        }

        nextCursor = null;

        if (!reviewList.getContent().isEmpty() && reviewList.hasNext()) {
            Review last = reviewList.getContent().get(reviewList.getNumberOfElements() - 1);
            switch (query.toLowerCase()) {
                case "id":
                    nextCursor =  last.getId() + ":" + last.getId();
                    break;
                case "rate":
                    nextCursor = last.getRate() + ":" + last.getId();
                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            };
        }

        // 리뷰들 DTO로 포장하기
        return PageConverter.toPaginationWithCursor(
                reviewRepository.countReviewByUser_Id(dto.userId()),
                reviewList.map(ReviewConverter::toGetMyReviewItem).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );
    }
}
