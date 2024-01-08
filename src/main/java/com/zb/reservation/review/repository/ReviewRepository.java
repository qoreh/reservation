package com.zb.reservation.review.repository;

import com.zb.reservation.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT round (avg(r.score), 1) FROM Review r WHERE r.storeId = :storeId")
    double findScoreAvgByStoreId(long storeId);

    Optional<Review> findByReservationId(long reservationId);
}
