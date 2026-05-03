package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.TermIsMust;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "term_name", nullable = false)
    private String termName;

    @Column(name = "term_body", nullable = false)
    private String termBody;

    @Column(name = "is_must", nullable = false)
    @Enumerated(EnumType.STRING)
    private TermIsMust isMust;
}
