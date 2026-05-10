package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.RegionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.converter.PageConverter;
import com.example.umc10th.global.dto.PageResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    // 미션 조회
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


    // 미션 생성
    @Override
    @Transactional
    public MissionResDTO.Create createMissions(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        // 미션 생성
        Mission mission = MissionConverter.toMission(store, dto);

        // 미션 DB 저장
        missionRepository.save(mission);

        return MissionConverter.toCreateResponse(mission);
    }

    // 가게 미션 조회
    @Override
    public PageResDTO.PaginationWithOffset<MissionResDTO.GetStoreMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        // 가게 찾기
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.STORE_NOT_FOUND));

        // 정렬기준 설정
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 가게 내 미션들 조회(Page)
        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        return PageConverter.toPaginationWithOffset(
                missionList.map(MissionConverter::toGetStoreMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    // 내 미션 조회
    @Override
    public PageResDTO.PaginationWithOffset<MissionResDTO.MyMissionItem> getMyMissions(
            MissionIsCompleted isComplete,
            Integer pageSize,
            Integer pageNumber,
            String sort,
            MissionReqDTO.GetMyMission dto
    ) {
        // 유저 찾기
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.USER_NOT_FOUND));

        // 정렬기준 설정
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 내 미션 조회
        Page<UserMission> missionList = userMissionRepository.findUserMissionsByIdWithOffset(dto.userId(),isComplete, pageRequest);

        return PageConverter.toPaginationWithOffset(
                missionList.map(MissionConverter::toGetMyMissionItem).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

}
