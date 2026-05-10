package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.global.dto.PageResDTO;

public interface MissionService {

    MissionResDTO.GetList getMissions(
            Long regionId,
            Long userId,
            Long cursor,
            Integer limit,
            String isComplete
    );

//    MissionResDTO.GetProgressMission getProgressMission(
//            Long userId,
//            Integer pageSize,
//            Integer pageNumber,
//            String sort
//    );

    // 미션 생성
    MissionResDTO.Create createMissions(
            Long storeId,
            MissionReqDTO.CreateMission dto
    );

    // 가게 미션 조회
    PageResDTO.PaginationWithOffset<MissionResDTO.GetStoreMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    );


    // 내 미션 조회
    PageResDTO.PaginationWithOffset<MissionResDTO.MyMissionItem> getMyMissions(
            MissionIsCompleted isComplete,
            Integer pageSize,
            Integer pageNumber,
            String sort,
            MissionReqDTO.GetMyMission dto
    );
}
