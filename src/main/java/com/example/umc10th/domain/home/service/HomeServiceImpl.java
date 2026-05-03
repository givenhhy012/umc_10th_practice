package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.converter.HomeConverter;
import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.exception.HomeException;
import com.example.umc10th.domain.home.exception.code.HomeErrorCode;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Region;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.User;
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
public class HomeServiceImpl implements HomeService {

    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public HomeResDTO.GetHome getHome(Long regionId, Long userId, Long cursor, Integer limit) {
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new HomeException(HomeErrorCode.REGION_NOT_FOUND));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HomeException(HomeErrorCode.USER_NOT_FOUND));

        long completedCount = userMissionRepository.countByUserIdAndIsCompleted(
                userId, MissionIsCompleted.TRUE);

        Pageable pageable = PageRequest.of(0, limit + 1);
        List<Mission> fetched = missionRepository.findAvailableMissionsInRegion(
                regionId, userId, cursor, pageable);

        boolean hasNext = fetched.size() > limit;
        List<Mission> missions = hasNext ? fetched.subList(0, limit) : fetched;
        Long nextCursor = hasNext ? missions.get(missions.size() - 1).getId() : null;

        return HomeConverter.toGetHome(
                region,
                user.getUserPoint(),
                (int) completedCount,
                missions,
                nextCursor,
                hasNext
        );
    }
}
