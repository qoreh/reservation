package com.zb.reservation.reservation.controller;

import com.zb.reservation.reservation.dto.ReservationDto;
import com.zb.reservation.reservation.dto.StatusUpdateDto;
import com.zb.reservation.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ManagerReservationController {
    private final ReservationService reservationService;

    /**
     * 예약 요청 목록 조회
     */
    @GetMapping("/manager/reservation/request/list")
    public ResponseEntity<?> requestList(@RequestParam long storeId) {
        List<ReservationDto> requestList = reservationService.getRequestList(storeId);

        return new ResponseEntity<>(requestList, HttpStatus.OK);
    }

    /**
     * 예약 승인 / 거절, 상태 변경
     */
    @PostMapping("/manager/reservation/update")
    public ResponseEntity<?> updateStatus(@RequestBody StatusUpdateDto statusUpdateDto) {
        ReservationDto updated = reservationService.updateStatus(statusUpdateDto);

        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
