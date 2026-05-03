package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;

public class UserConverter {

    public static User toEntity(UserReqDTO.SignUp request) {
        Gender gender;
        try {
            gender = Gender.valueOf(request.gender());
        } catch (IllegalArgumentException e) {
            throw new UserException(UserErrorCode.INVALID_GENDER);
        }

        String fullAddress = (request.detailAddress() != null && !request.detailAddress().isBlank())
                ? request.address() + " " + request.detailAddress()
                : request.address();

        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .gender(gender)
                .birthDate(request.birthDate())
                .address(fullAddress)
                .build();
    }

    public static UserResDTO.SignUp toSignUpResponse(User user) {
        return UserResDTO.SignUp.builder()
                .userId(user.getId())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public static UserResDTO.GetInfo toGetInfoResponse(User user) {
        return UserResDTO.GetInfo.builder()
                .name(user.getName())
                .profileUrl(user.getProfileUrl())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNum())
                .point(user.getUserPoint())
                .build();
    }
}
