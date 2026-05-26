package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    // 마이페이지
    @GetMapping("/users/me")
    public ApiResponse<UserResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthUser user
            ) {
        UserResDTO.GetInfo result = userService.getMyInfo(user);
        BaseSuccessCode code = UserSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }

    // 회원가입
    @PostMapping("/auth/users")
    public ApiResponse<UserResDTO.SignUp> signUp(
            @RequestBody UserReqDTO.SignUp request
    ) {
        UserResDTO.SignUp result = userService.signUp(request);
        BaseSuccessCode code = UserSuccessCode.SIGNUP_OK;
        return ApiResponse.onSuccess(code, result);
    }

    // 로그인
    @PostMapping("/auth/login")
    public ApiResponse<UserResDTO.Login> login(
            @RequestBody UserReqDTO.Login request
    ) {
        UserResDTO.Login result = userService.logIn(request);
        BaseSuccessCode code = UserSuccessCode.LOGIN_OK;
        return ApiResponse.onSuccess(code, result);
    }
}
