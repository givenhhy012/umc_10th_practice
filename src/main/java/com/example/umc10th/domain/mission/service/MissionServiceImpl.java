package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private final UserMissionRepository userMissionRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    @Override
    public MissionResDTO.GetList getMissions(
            Long regionId,
            Long userId,
            Long cursor,
            Integer limit,
            String isComplete
    ) {
        if (!regionRepository.existsById(regionId)) {
            throw new MissionException(MissionErrorCode.REGION_NOT_FOUND);
        }
        if (!userRepository.existsById(userId)) {
            throw new MissionException(MissionErrorCode.USER_NOT_FOUND);
        }

        MissionIsCompleted isCompletedEnum;
        try {
            isCompletedEnum = MissionIsCompleted.valueOf(isComplete);
        } catch (IllegalArgumentException e) {
            throw new MissionException(MissionErrorCode.INVALID_IS_COMPLETED);
        }

        Pageable pageable = PageRequest.of(0, limit + 1);
        List<UserMission> fetched = userMissionRepository.findUserMissionsByRegionWithCursor(
                regionId, userId, isCompletedEnum, cursor, pageable);

        boolean hasNext = fetched.size() > limit;
        List<UserMission> userMissions = hasNext ? fetched.subList(0, limit) : fetched;
        Long nextCursor = hasNext ? userMissions.get(userMissions.size() - 1).getId() : null;

        return MissionConverter.toGetList(regionId, isComplete, userMissions, nextCursor, hasNext);
    }
}
