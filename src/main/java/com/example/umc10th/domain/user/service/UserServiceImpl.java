package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.Food;
import com.example.umc10th.domain.user.entity.Term;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.mapping.UserFood;
import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import com.example.umc10th.domain.user.enums.TermIsMust;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTermRepository userTermRepository;
    private final TermRepository termRepository;
    private final UserFoodRepository userFoodRepository;
    private final FoodRepository foodRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResDTO.SignUp signUp(UserReqDTO.SignUp request) {

        // 이미 존재하는 유저인지 검사(이메일로 중복 여부 판단)
        if (userRepository.existsByEmail(request.email())) {
            throw new UserException(UserErrorCode.EMAIL_DUPLICATED);
        }

        // 리퀘스트 바디로 받은 약관의 id가 존재하는 약관인지 검사
        List<Long> requestedTermIds = request.agreeList().stream()
                .map(UserReqDTO.Agree::termId)
                .toList();

        List<Long> existsTermIds = termRepository.findAll().stream()
                .map(Term::getId)
                .toList();

        if(!existsTermIds.containsAll(requestedTermIds))
            throw new UserException(UserErrorCode.TERM_NOT_FOUND);


        // 필수 약관 동의 여부 검증
        List<Long> agreedTermIds = request.agreeList().stream()
                .filter(UserReqDTO.Agree::agreed)
                .map(UserReqDTO.Agree::termId)
                .toList();

        List<Long> requiredTermIds = termRepository.findByIsMust(TermIsMust.MUST).stream()
                .map(Term::getId)
                .toList();

        if(!agreedTermIds.containsAll(requiredTermIds))
            throw new UserException(UserErrorCode.TERM_NOT_AGREED);


        // 유저 생성
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = UserConverter.toUserEntity(request, encodedPassword);
        userRepository.save(user);

        // 유저 약관 저장
        List<UserTerm> userterms = requestedTermIds.stream()
                .map(termId -> {
                    Term term = termRepository.findById(termId)
                            .orElseThrow(() -> new UserException(UserErrorCode.TERM_NOT_FOUND));
                    return UserTerm.builder()
                            .user(user)
                            .term(term)
                            .agreed(true)
                            .build();
                }).toList();
        userTermRepository.saveAll(userterms);


        // 선호 음식 처리
        List<UserFood> userFoods = request.foodList().stream()
                .map( foodCategory-> {
                    Food food = foodRepository.findByFoodCategory(foodCategory)
                            .orElseThrow(()->new UserException(UserErrorCode.FOOD_NOT_FOUND));
                    return UserFood.builder()
                            .user(user)
                            .food(food)
                            .build();
                }).toList();
        userFoodRepository.saveAll(userFoods);


        return UserConverter.toSignUpResponse(user);
    }

    @Override
    public UserResDTO.GetInfo getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserConverter.toGetInfoResponse(user);
    }
}
