package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.dto.PageResDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 지역에 따른 미션 목록 조회 (진행중 / 진행 완료)
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.GetList> getMissions(
            @RequestParam Long regionId,
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam String isComplete
    ) {
        MissionResDTO.GetList result = missionService.getMissions(regionId, userId, cursor, limit, isComplete);
        BaseSuccessCode code = MissionSuccessCode.LIST_OK;
        return ApiResponse.onSuccess(code, result);
    }

    // 미션 성공 처리
    @PatchMapping("/missions/{mission-id}")
    public ApiResponse<MissionResDTO.Complete> completeMission(
            @PathVariable("mission-id") Long missionId,
            @RequestBody MissionReqDTO.Complete request
    ) {
        BaseSuccessCode code = MissionSuccessCode.COMPLETE_OK;
        return ApiResponse.onSuccess(code, null);
    }


    // 가게 미션 생성
    @PostMapping("/stores/{stores-id}/missions")
    public ApiResponse<MissionResDTO.Create> createMission(
            @PathVariable("stores-id") Long storeId,
            @RequestBody MissionReqDTO.CreateMission dto
    ) {
        MissionResDTO.Create result = missionService.createMissions(storeId, dto);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_CREATED;
        return ApiResponse.onSuccess(code, result);
    }

    // 가게 미션 조회(오프셋 페이징)
    @GetMapping("/stores/{stores-id}/missions")
    public ApiResponse<PageResDTO.PaginationWithOffset<MissionResDTO.GetStoreMission>> getStoreMission(
            @PathVariable("stores-id") Long storeId, // (자바)변수명과 URL의 변수명이 다를경우 ("stores-id") 이런식으로 명시해줘야함.
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        PageResDTO.PaginationWithOffset<MissionResDTO.GetStoreMission> result = missionService.getStoreMissions(storeId, pageSize, pageNumber, sort);
        BaseSuccessCode code = MissionSuccessCode.STORE_MISSION_OK;
        return ApiResponse.onSuccess(code, result);
    }


    // 내가 진행중 or 진행 완료한 미션 조회
    @PostMapping("/missions")
    public ApiResponse<PageResDTO.PaginationWithOffset<MissionResDTO.MyMissionItem>> getMyMission(
            @RequestParam MissionIsCompleted isComplete,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort,
            @RequestBody @Valid MissionReqDTO.GetMyMission dto
    ) {
        PageResDTO.PaginationWithOffset<MissionResDTO.MyMissionItem> result = missionService.getMyMissions(isComplete, pageSize, pageNumber, sort, dto);
        BaseSuccessCode code = MissionSuccessCode.MY_MISSION_OK;
        return ApiResponse.onSuccess(code, result);
    }


}
