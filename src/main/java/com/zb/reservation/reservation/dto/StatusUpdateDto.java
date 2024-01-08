package com.zb.reservation.reservation.dto;

import com.zb.reservation.reservation.type.ReservationStatus;
import lombok.Data;

@Data
public class StatusUpdateDto {

    private long reservationId;

    private ReservationStatus status;
}
