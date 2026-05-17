package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.enums.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByFoodCategory(FoodCategory category);
}
