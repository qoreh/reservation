package com.zb.reservation.reservation.service;

import com.zb.reservation.reservation.dto.ReservationDto;
import com.zb.reservation.reservation.dto.StatusUpdateDto;
import com.zb.reservation.reservation.dto.VisitCheckDto;

import java.util.List;

public interface ReservationService {

    /**
     * 예약 가능 여부 체크
     */
    boolean check(ReservationDto reservationDto);

    /**
     * 예약 신청
     */
    ReservationDto request(ReservationDto reservationDto);

    /**
     * 예약 신청 목록 조회
     */
    List<ReservationDto> getRequestList(long storeId);

    /**
     * 예약 상태 변경
     */
    ReservationDto updateStatus(StatusUpdateDto statusUpdateDto);

    /**
     * 방문 확인
     */
    void visit(VisitCheckDto visitCheckDto);


}
