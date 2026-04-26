package com.example.umc10th.domain.home.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class HomeException extends ProjectException {
    public HomeException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
