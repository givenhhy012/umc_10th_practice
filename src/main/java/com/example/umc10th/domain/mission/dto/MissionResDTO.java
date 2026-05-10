package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 지역별 미션 조회
    @Builder
    public record GetList(
            Long regionId,
            String isCompleted,
            List<MissionItem> missions,
            Long nextCursor,
            Boolean hasNext
    ) {}

    // 지역별 각 미션 아이템들 정보
    @Builder
    public record MissionItem(
            Long missionId,
            Long storeId,
            String storeName,
            Integer missionPoint,
            String missionDescription,
            LocalDate missionDeadline,
            MissionIsCompleted isCompleted
    ) {}

    // 미션 성공 처리
    @Builder
    public record Complete(
            Long missionRecordId,
            Long missionId,
            String isCompleted,
            LocalDateTime completedAt
    ) {}

    // 가게 내 미션 조회
    @Builder
    public record GetStoreMission(
            Long missionId,
            Integer point,
            String missionDescription
    ) {}

//    // 페이지네이션 틀(오프셋)
//    @Builder
//    public record Pagination<T>(
//            List<T> data,
//            Integer pageNumber,
//            Integer pageSize
//    ) {}

    // 미션 생성
    @Builder
    public record Create(
            Long missionId,
            LocalDateTime createdAt
    ) {}

    // 내 미션 조회용 미션 아이템 정보
    @Builder
    public record MyMissionItem(
            Long missionId,
            Long storeId,
            String storeName,
            Integer missionPoint,
            String missionDescription,
            LocalDate missionDeadline,
            MissionIsCompleted isCompleted,
            LocalDateTime missionCreatedAt,
            LocalDateTime missionAcceptedAt
    ) {}

}
