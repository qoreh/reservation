package com.zb.reservation.user.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    MEMBER_STATUS_ING("ING"),
    MEMBER_STATUS_STOP("STOP"),
    MEMBER_STATUS_WITHDRAW("WITHDRAW");

    private final String code;
}
