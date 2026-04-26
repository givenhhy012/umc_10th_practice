package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetList(
            Long regionId,
            String isCompleted,
            List<MissionItem> missions,
            Long nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record MissionItem(
            Long missionId,
            Long storeId,
            String storeName,
            Integer missionPoint,
            String missionDescription,
            LocalDate missionDeadline,
            String isCompleted
    ) {}

    @Builder
    public record Complete(
            Long missionRecordId,
            Long missionId,
            String isCompleted,
            LocalDateTime completedAt
    ) {}
}
