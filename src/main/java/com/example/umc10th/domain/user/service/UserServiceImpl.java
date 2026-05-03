package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserException(UserErrorCode.EMAIL_DUPLICATED);
        }

        User user = UserConverter.toEntity(request);
        User saved = userRepository.save(user);

        return UserConverter.toSignUpResponse(saved);
    }

    @Override
    public UserResDTO.GetInfo getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toGetInfoResponse(user);
    }
}
