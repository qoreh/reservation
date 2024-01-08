package com.zb.reservation.reservation.entity;

import com.zb.reservation.reservation.type.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    long storeId;

    String userId;

    LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    ReservationStatus status;

}
