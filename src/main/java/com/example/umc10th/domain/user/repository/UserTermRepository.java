package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermRepository extends JpaRepository<UserTerm, Long> {

}
