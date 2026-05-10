package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
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
                .isCompleted(userMission.getIsCompleted())
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

    // 미션 생성을 위한
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .missionName(dto.missionName())
                .missionDescription(dto.missionDescription())
                .missionPoint(dto.missionPoint())
                .deadline(dto.deadline())
                .build();
    }

    // 미션 생성 성공 후 응답용
    public static MissionResDTO.Create toCreateResponse(Mission mission) {
        return MissionResDTO.Create.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetStoreMission toGetStoreMission(Mission mission) {
        return MissionResDTO.GetStoreMission.builder()
                .missionDescription(mission.getMissionDescription())
                .point(mission.getMissionPoint())
                .missionId(mission.getId())
                .build();
    }

//    // 페이지네이션 틀 생성(오프셋)
//    public static <T> MissionResDTO.Pagination<T> toPagination(
//            List<T> data,
//            Integer pageNumber,
//            Integer pageSize
//    ) {
//        return MissionResDTO.Pagination.<T>builder()
//                .data(data)
//                .pageNumber(pageNumber)
//                .pageSize(pageSize)
//                .build();
//    }


    // 내 미션 조회
    public static MissionResDTO.MyMissionItem toGetMyMissionItem(UserMission userMission) {
        Mission mission = userMission.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MyMissionItem.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .missionPoint(mission.getMissionPoint())
                .missionDescription(mission.getMissionDescription())
                .missionDeadline(mission.getDeadline())
                .isCompleted(userMission.getIsCompleted())
                .missionCreatedAt(mission.getCreatedAt())
                .missionAcceptedAt(userMission.getCreatedAt())
                .build();
    }
//
//    // 내 미션 조회 리스트
//    public static MissionResDTO.GetMyMission toGetMyMission(
//            List<UserMission> userMissions
//    ) {
//        List<MissionResDTO.MyMissionItem> items = userMissions.stream()
//                .map(MissionConverter::toGetMyMissionItem)
//                .toList();
//
//        return MissionResDTO.GetMyMission.builder()
//                .missions(items)
//                .build();
//    }
}
