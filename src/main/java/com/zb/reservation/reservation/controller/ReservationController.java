package com.zb.reservation.reservation.controller;

import com.zb.reservation.reservation.dto.ReservationDto;
import com.zb.reservation.reservation.dto.VisitCheckDto;
import com.zb.reservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 예약 가능 여부 체크
     */
    @GetMapping("/reservation/check")
    public ResponseEntity<?> checkPossible(@RequestBody ReservationDto reservationDto) {
        boolean isPossible = reservationService.check(reservationDto);

        return new ResponseEntity<>(isPossible, HttpStatus.OK);
    }

    /**
     * 예약 신청
     */
    @PostMapping("/reservation/request")
    public ResponseEntity<?> reservationRequest(@RequestBody ReservationDto reservationDto) {
        ReservationDto reservation = reservationService.request(reservationDto);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    /**
     * 방문 확인
     */
    @PostMapping("/reservation/visit")
    public ResponseEntity<?> visit(@RequestBody VisitCheckDto visitCheckDto) {
        reservationService.visit(visitCheckDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
