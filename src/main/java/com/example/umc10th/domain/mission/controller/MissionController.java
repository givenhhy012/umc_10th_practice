package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    // 미션 목록 조회 (진행중 / 진행 완료)
    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.GetList> getMissions(
            @RequestParam Long regionId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam String isComplete
    ) {
        BaseSuccessCode code = MissionSuccessCode.LIST_OK;
        return ApiResponse.onSuccess(code, null);
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
}
