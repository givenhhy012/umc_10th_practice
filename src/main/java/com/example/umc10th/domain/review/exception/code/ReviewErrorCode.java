package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 사용자를 찾을 수 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_2",
            "해당 가게를 찾을 수 없습니다."),

    // BAD_REQUEST
    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "유효하지 않은 정렬 기준입니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
