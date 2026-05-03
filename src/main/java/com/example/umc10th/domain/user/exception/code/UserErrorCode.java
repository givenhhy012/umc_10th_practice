package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    EMAIL_DUPLICATED(HttpStatus.CONFLICT,
            "USER409_1",
            "이미 가입된 이메일입니다."),
    INVALID_GENDER(HttpStatus.BAD_REQUEST,
            "USER400_1",
            "유효하지 않은 성별 값입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
