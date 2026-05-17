package com.example.umc10th.domain.user.converter;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;

public class UserConverter {

    // 유저 엔티티 생성을 위한 컨버터
    public static User toUserEntity(UserReqDTO.SignUp request, String encodedPassword) {

        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .gender(request.gender())
                .birthDate(request.birth())
                .address(request.address())
                .detailAdress(request.detailAddress())
                .build();
    }

    // 유저 약관 동의 여부(UserTerm) 엔티티 생성을 위한
//    public static UserTerm toUserTermEntity(User user, List<UserReqDTO.Agree> requestTerms){
//        return UserTerm.builder()
//                .
//    }

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
