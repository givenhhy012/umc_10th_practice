package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;

public interface UserService {

    UserResDTO.SignUp signUp(UserReqDTO.SignUp request);

    UserResDTO.GetInfo getMyInfo(Long userId);

    UserResDTO.Login logIn(UserReqDTO.Login request);
}
