package com.zb.reservation.reservation.dto;

import com.zb.reservation.reservation.type.ReservationStatus;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class ReservationDto {

    private long reservationId;

    private long storeId;

    private String userId;

    private LocalDateTime reservationTime;

    private ReservationStatus status;


}
