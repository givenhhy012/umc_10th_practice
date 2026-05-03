package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.enums.FoodCategory;
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
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodCategory foodCategory;
}
