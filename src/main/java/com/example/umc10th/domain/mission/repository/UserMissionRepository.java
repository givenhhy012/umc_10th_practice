package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
            SELECT um FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE s.region.id = :regionId
              AND um.user.id = :userId
              AND um.isCompleted = :isCompleted
              AND (:cursor IS NULL OR um.id < :cursor)
            ORDER BY um.id DESC
            """)
    List<UserMission> findUserMissionsByRegionWithCursor(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId,
            @Param("isCompleted") MissionIsCompleted isCompleted,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
            SELECT COUNT(um) FROM UserMission um
            WHERE um.user.id = :userId
              AND um.isCompleted = :isCompleted
            """)
    long countByUserIdAndIsCompleted(
            @Param("userId") Long userId,
            @Param("isCompleted") MissionIsCompleted isCompleted
    );


    // 내 미션 조회
    @Query("""
            SELECT um
            FROM UserMission um
            JOIN FETCH um.mission m
            JOIN FETCH m.store s
            WHERE um.user.id = :userId
              AND um.isCompleted = :isCompleted
            """)
    Page<UserMission> findUserMissionsByIdWithOffset(
            @Param("userId") Long userId,
            @Param("isCompleted") MissionIsCompleted isCompleted,
            Pageable pageable
    );
}
