package com.zb.reservation.user.dto;

import com.zb.reservation.user.type.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class SignInResponseDto {

    private String userId;

    private Set<UserRole> roles;

}
