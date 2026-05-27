package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String username);

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String uid);
}
