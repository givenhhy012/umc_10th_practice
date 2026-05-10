package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> id(Long id);

    Slice<Review> findReviewByUser_IdAndIdLessThanOrderByIdDesc(Long aLong, long idCursor, PageRequest pageRequest);

    Slice<Review> findReviewByUser_IdOrderByIdDesc(Long aLong, PageRequest pageRequest);

    @Query("""
        SELECT r FROM Review r
        WHERE r.user.id = :userId
          AND (r.rate < :rateCursor 
               OR (r.rate = :rateCursor AND r.id < :idCursor))
        ORDER BY r.rate DESC, r.id DESC
        """)
    Slice<Review> findByUserIdWithRateCursor(
            @Param("userId") Long userId,
            @Param("rateCursor") Float rateCursor,
            @Param("idCursor") Long idCursor,
            Pageable pageable
    );

    long countReviewByUser_Id(Long userId);

    Slice<Review> findReviewByUser_IdOrderByRateDescIdDesc(Long aLong, PageRequest pageRequest);
}
