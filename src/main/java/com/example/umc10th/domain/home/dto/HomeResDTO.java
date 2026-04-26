package com.example.umc10th.domain.home.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class HomeResDTO {

    @Builder
    public record GetHome(
            Long regionId,
            String regionName,
            Integer userPoint,
            Integer completedCount,
            List<MissionSummary> missions,
            Long nextCursor,
            Boolean hasNext
    ) {}

    @Builder
    public record MissionSummary(
            Long missionId,
            Long storeId,
            String storeName,
            String storeCategory,
            Integer missionPoint,
            String missionDescription,
            LocalDate missionDeadline
    ) {}
}
