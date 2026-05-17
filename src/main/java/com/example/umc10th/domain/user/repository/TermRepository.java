package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.enums.TermIsMust;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermRepository extends JpaRepository<Term, Long> {


    List<Term> findByIsMust(TermIsMust termIsMust);

    int countByIdIn(List<Long> ids);
}
