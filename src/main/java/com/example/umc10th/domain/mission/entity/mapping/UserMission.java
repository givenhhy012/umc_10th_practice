package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.MissionIsCompleted;
import com.example.umc10th.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_mission")
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_date", nullable = false)
    private LocalDateTime missionDate;

    @Column(name = "is_completed", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionIsCompleted isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;
}
