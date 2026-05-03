package com.example.umc10th.domain.home.converter;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.entity.Store;

import java.util.List;

public class HomeConverter {

    public static HomeResDTO.MissionSummary toMissionSummary(Mission mission) {
        Store store = mission.getStore();

        return HomeResDTO.MissionSummary.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .storeCategory(null)
                .missionPoint(mission.getMissionPoint())
                .missionDescription(mission.getMissionDescription())
                .missionDeadline(mission.getDeadline())
                .build();
    }

    public static HomeResDTO.GetHome toGetHome(
            Region region,
            Integer userPoint,
            Integer completedCount,
            List<Mission> missions,
            Long nextCursor,
            Boolean hasNext
    ) {
        List<HomeResDTO.MissionSummary> items = missions.stream()
                .map(HomeConverter::toMissionSummary)
                .toList();

        return HomeResDTO.GetHome.builder()
                .regionId(region.getId())
                .regionName(region.getRegionName())
                .userPoint(userPoint)
                .completedCount(completedCount)
                .missions(items)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
