package com.zb.reservation.reservation.repository;

import com.zb.reservation.reservation.entity.Reservation;
import com.zb.reservation.reservation.type.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStoreIdAndReservationTime(long storeId, LocalDateTime reservationTime);

    List<Reservation> findByStoreIdAndStatus(long storeId, ReservationStatus status);

}
