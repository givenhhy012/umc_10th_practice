package com.example.umc10th.global.dto;

import lombok.Builder;

import java.util.List;

public class PageResDTO {

    // 페이지네이션 틀(오프셋)
    @Builder
    public record PaginationWithOffset<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}


    // 페이지네이션 틀(커서)
    @Builder
    public record PaginationWithCursor<T>(
            Long totalCount,    // 내가 작성한 리뷰 총 개수를 위한
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {}
}
