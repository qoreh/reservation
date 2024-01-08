package com.zb.reservation.user.service;

import com.zb.reservation.exception.CustomException;
import com.zb.reservation.exception.ErrorCode;
import com.zb.reservation.user.dto.Auth;
import com.zb.reservation.user.dto.SignInResponseDto;
import com.zb.reservation.user.entity.User;
import com.zb.reservation.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(Auth.SignUpDto signUp) {
        // 아이디 중복 체크
        Optional<User> userId = userRepository.findByUserId(signUp.getUserId());
        if (userId.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_ID);
        }

        // 휴대번호 중복 체크
        Optional<User> phone = userRepository.findByPhone(signUp.getPhone());
        if (phone.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXIST_PHONE);
        }

        // 패스워드 암호화
        String encryptedPassword = BCrypt.hashpw(signUp.getPassword(), BCrypt.gensalt());
        signUp.setPassword(encryptedPassword);

        // 엔티티화 & 저장
        userRepository.save(signUp.toEntity());

    }

    @Override
    public SignInResponseDto signIn(Auth.SignInDto request) {
        // 유저 검색
        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)
                );

        // 패스워드 일치 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }

        return user.from();
    }

}
