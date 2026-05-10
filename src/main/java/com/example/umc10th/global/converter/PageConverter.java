package com.example.umc10th.global.converter;

import com.example.umc10th.global.dto.PageResDTO;

import java.util.List;

public class PageConverter {
    // 페이지네이션 틀 생성(오프셋)
    public static <T> PageResDTO.PaginationWithOffset<T> toPaginationWithOffset(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return PageResDTO.PaginationWithOffset.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    // 페이지네이션 틀 생성(커서)
    public static <T> PageResDTO.PaginationWithCursor<T> toPaginationWithCursor(
            Long totalCount,    // 내가 작성한 리뷰 총 개수를 위한
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return PageResDTO.PaginationWithCursor.<T>builder()
                .totalCount(totalCount)
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
