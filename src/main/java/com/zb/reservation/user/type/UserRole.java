package com.zb.reservation.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_USER("ROLE_USER");

    private final String code;

}

