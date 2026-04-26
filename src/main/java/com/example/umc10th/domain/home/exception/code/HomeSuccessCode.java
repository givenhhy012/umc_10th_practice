package com.example.umc10th.domain.home.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HomeSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "HOME200_1",
            "성공적으로 홈화면을 조회했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
