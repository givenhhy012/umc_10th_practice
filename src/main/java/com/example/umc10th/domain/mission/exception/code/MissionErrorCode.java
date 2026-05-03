package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "해당 지역을 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_2",
            "해당 사용자를 찾을 수 없습니다."),
    INVALID_IS_COMPLETED(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "유효하지 않은 isCompleted 값입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
