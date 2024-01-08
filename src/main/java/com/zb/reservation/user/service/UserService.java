package com.zb.reservation.user.service;

import com.zb.reservation.user.dto.Auth;
import com.zb.reservation.user.dto.SignInResponseDto;

public interface UserService {
    /**
     * 회원 가입
     */
    void signUp(Auth.SignUpDto signUp);

    /**
     * 로그인을 위해 패스워드 확인
     */
    SignInResponseDto signIn(Auth.SignInDto request);
}
