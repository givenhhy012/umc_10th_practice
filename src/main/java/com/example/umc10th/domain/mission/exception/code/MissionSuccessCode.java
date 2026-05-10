package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    // OK
    LIST_OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션 목록을 조회했습니다."),
    COMPLETE_OK(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션을 완료 처리했습니다."),
    STORE_MISSION_OK(HttpStatus.OK,
            "MISSION200_3",
            "성공적으로 가게의 미션을 조회했습니다."),
    MY_MISSION_OK(HttpStatus.OK,
            "MISSION200_4",
            "성공적으로 내 미션을 조회했습니다."),

    // CREATED
    STORE_MISSION_CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
