package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.global.security.entity.AuthUser;

public interface UserService {

    UserResDTO.SignUp signUp(UserReqDTO.SignUp request);

    UserResDTO.GetInfo getMyInfo(AuthUser user);

    UserResDTO.Login logIn(UserReqDTO.Login request);
}
