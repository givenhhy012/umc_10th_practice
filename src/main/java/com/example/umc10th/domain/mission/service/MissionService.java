package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;

public interface MissionService {

    MissionResDTO.GetList getMissions(
            Long regionId,
            Long userId,
            Long cursor,
            Integer limit,
            String isComplete
    );
}
