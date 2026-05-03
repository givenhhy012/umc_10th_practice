package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionItem toMissionItem(UserMission userMission) {
        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MissionItem.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .missionPoint(mission.getMissionPoint())
                .missionDescription(mission.getMissionDescription())
                .missionDeadline(mission.getDeadline())
                .isCompleted(userMission.getIsCompleted().name())
                .build();
    }

    public static MissionResDTO.GetList toGetList(
            Long regionId,
            String isCompleted,
            List<UserMission> userMissions,
            Long nextCursor,
            Boolean hasNext
    ) {
        List<MissionResDTO.MissionItem> items = userMissions.stream()
                .map(MissionConverter::toMissionItem)
                .toList();

        return MissionResDTO.GetList.builder()
                .regionId(regionId)
                .isCompleted(isCompleted)
                .missions(items)
                .nextCursor(nextCursor)
                .hasNext(hasNext)
                .build();
    }
}
