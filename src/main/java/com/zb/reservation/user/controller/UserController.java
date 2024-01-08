package com.zb.reservation.user.controller;

import com.zb.reservation.user.dto.ErrorResponse;
import com.zb.reservation.security.TokenProvider;
import com.zb.reservation.user.dto.Auth;
import com.zb.reservation.user.dto.SignInResponseDto;
import com.zb.reservation.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenProvider tokenProvider;

    /**
     * 회원가입
     * 일반 회원으로 가입할건지 점장으로 가입할지 여부를 옵션으로 선택한다고 가정하고 진행
     * 일반 회원 선택시 UserRole -> ROLE_USER, 점장 선택시 UserRole -> ROLE_MANAGER
     */
    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid Auth.SignUpDto request, Errors errors) {
        if (errors.hasErrors()) {
            return checkError(errors);
        }

        userService.signUp(request);

        return ResponseEntity.ok().build();
    }

    /**
     * 로그인
     */
    @GetMapping("/user/signIn")
    public ResponseEntity<?> signIn(@RequestBody @Valid Auth.SignInDto request, Errors errors) {
        if (errors.hasErrors()) {
            return checkError(errors);
        }

        SignInResponseDto response = userService.signIn(request);
        String token = tokenProvider.generateToken(response.getUserId(), response.getRoles());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * 입력받은 데이터 검증 중 오류가 있는 지 체크하는 함수
     */
    public ResponseEntity<?> checkError(Errors errors) {
        List<ErrorResponse> responseList = new ArrayList<>();
        errors.getAllErrors().forEach((e) -> {
            responseList.add(ErrorResponse.of((FieldError) e));
        });

        return new ResponseEntity<>(responseList, HttpStatus.BAD_REQUEST);
    }

}
