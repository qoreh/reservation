package com.zb.reservation.reservation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitCheckDto {

    private String code;

    private long storeId;

    private long reservationId;

    private LocalDateTime visitTime;

}
