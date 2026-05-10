package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            SELECT m FROM Mission m
            JOIN FETCH m.store s
            WHERE s.region.id = :regionId
              AND NOT EXISTS (
                  SELECT 1 FROM UserMission um
                  WHERE um.mission = m AND um.user.id = :userId
              )
              AND (:cursor IS NULL OR m.id < :cursor)
            ORDER BY m.id DESC
            """)
    List<Mission> findAvailableMissionsInRegion(
            @Param("regionId") Long regionId,
            @Param("userId") Long userId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );


    Page<Mission> findAllByStore_Id(Long storeId, Pageable pageable);

}
