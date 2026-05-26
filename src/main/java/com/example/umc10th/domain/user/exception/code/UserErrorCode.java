package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    // 400 BAD_REQUEST
    INVALID_GENDER(HttpStatus.BAD_REQUEST,
            "USER400_1",
            "유효하지 않은 성별 값입니다."),
    TERM_NOT_AGREED(HttpStatus.BAD_REQUEST,
            "USER400_2",
            "필수 약관에 동의하지 않았습니다."),

    // 401 UNAUTHORIZED
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED,
            "USER401_1",
            "아이디 또는 비밀번호가 일치하지 않습니다."),

    // 404 NOT_FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_1",
            "해당 사용자를 찾을 수 없습니다."),
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_2",
            "존재하지 않는 약관 id입니다."),
    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND,
            "USER404_3",
            "존재하지 않는 음식입니다."),

    // 409 CONFLICT
    EMAIL_DUPLICATED(HttpStatus.CONFLICT,
            "USER409_1",
            "이미 가입된 이메일입니다."),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
