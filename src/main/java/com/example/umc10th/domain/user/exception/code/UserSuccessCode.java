package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "USER200_1",
            "성공적으로 유저를 조회했습니다."),
    SIGNUP_OK(HttpStatus.CREATED,
            "USER201_1",
            "성공적으로 회원가입했습니다."),
    LOGIN_OK(HttpStatus.OK,
            "USER200_2",
            "성공적으로 로그인했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
