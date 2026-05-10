package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {

    // 진행중, 진행완료 여부
    @Builder
    public record Complete(
            String isCompleted
    ) {}

    // 가게 미션 생성
    @Builder
    public record CreateMission(
            String missionName,
            LocalDate deadline,
            Integer missionPoint,
            String missionDescription
    ) {}

    // 내 미션 조회
    @Builder
    public record GetMyMission(
            // 어노테이션을 통한 DTO단에서의 검증
            @NotNull(message = "아이디는 필수입니다.")
            Long userId
    ) {}
}
