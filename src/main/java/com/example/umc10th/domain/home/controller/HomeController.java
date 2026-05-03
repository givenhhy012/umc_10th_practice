package com.example.umc10th.domain.home.controller;

import com.example.umc10th.domain.home.dto.HomeResDTO;
import com.example.umc10th.domain.home.exception.code.HomeSuccessCode;
import com.example.umc10th.domain.home.service.HomeService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    // 홈화면
    @GetMapping("/home")
    public ApiResponse<HomeResDTO.GetHome> getHome(
            @RequestParam Long regionId,
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        HomeResDTO.GetHome result = homeService.getHome(regionId, userId, cursor, limit);
        BaseSuccessCode code = HomeSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }
}
