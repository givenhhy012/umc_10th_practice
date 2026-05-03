package com.example.umc10th.domain.home.service;

import com.example.umc10th.domain.home.dto.HomeResDTO;

public interface HomeService {

    HomeResDTO.GetHome getHome(Long regionId, Long userId, Long cursor, Integer limit);
}
