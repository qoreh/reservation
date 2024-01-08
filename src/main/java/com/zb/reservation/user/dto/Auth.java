package com.zb.reservation.user.dto;

import com.zb.reservation.user.type.UserRole;
import com.zb.reservation.user.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

import static com.zb.reservation.user.type.UserStatus.MEMBER_STATUS_ING;

public class Auth {

    @Data
    public static class SignInDto {
        @NotBlank(message = "아이디를 입력해주세요.")
        private String userId;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    @Data
    public static class SignUpDto {
        @NotBlank(message = "아이디는 필수 항목입니다.")
        private String userId;

        @Size(min = 4, message = "비밀번호는 4자리 이상 입력해주세요.")
        @NotBlank(message = "비밀번호는 필수 항목입니다.")
        private String password;

        @NotBlank(message = "이름은 필수 항목입니다.")
        private String userName;

        @NotBlank(message = "전화번호는 필수 항목입니다.")
        private String phone;

        @NotBlank(message = "메일은 필수 항목입니다.")
        private String email;

        private String zipCode;
        private String address;

        private Set<UserRole> roles;

        public User toEntity() {
            return User.builder()
                    .userId(this.userId)
                    .password(this.password)
                    .userName(this.userName)
                    .phone(this.phone)
                    .email(this.email)
                    .zipCode(this.zipCode)
                    .address(this.address)
                    .roles(this.roles)
                    .userStatus(MEMBER_STATUS_ING.getCode())
                    .regDate(LocalDateTime.now())
                    .build();
        }
    }
}
