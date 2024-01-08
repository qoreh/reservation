package com.zb.reservation.reservation.service;

import com.zb.reservation.exception.CustomException;
import com.zb.reservation.exception.ErrorCode;
import com.zb.reservation.reservation.dto.ReservationDto;
import com.zb.reservation.reservation.dto.StatusUpdateDto;
import com.zb.reservation.reservation.dto.VisitCheckDto;
import com.zb.reservation.reservation.entity.Reservation;
import com.zb.reservation.reservation.mapper.ReservationMapper;
import com.zb.reservation.reservation.repository.ReservationRepository;
import com.zb.reservation.reservation.type.ReservationStatus;
import com.zb.reservation.store.entity.Store;
import com.zb.reservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationMapper mapper = ReservationMapper.INSTANCE;

    @Override
    public boolean check(ReservationDto reservationDto) {
        // 해당 상점에 요청과 같은 시간대의 예약을 전부 조회
        List<Reservation> reservations = reservationRepository.findByStoreIdAndReservationTime(
                reservationDto.getStoreId(), reservationDto.getReservationTime());

        Store store = storeRepository.findById(reservationDto.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        // 상점의 예약 수용치와 조회한 예약의 개수를 비교하여 가능여부를 체크
        if (reservations.size() >= store.getCapacity()) {
            return false;
        }

        return true;
    }

    @Override
    public ReservationDto request(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.save(mapper.toEntity(reservationDto));

        return mapper.toDto(reservation);
    }

    @Override
    public List<ReservationDto> getRequestList(long storeId) {
        List<Reservation> reservationList = reservationRepository
                .findByStoreIdAndStatus(storeId, ReservationStatus.REQUEST);

        return mapper.toDtoList(reservationList);
    }

    @Override
    public ReservationDto updateStatus(StatusUpdateDto statusUpdateDto) {
        Reservation reservation = reservationRepository
                .findById(statusUpdateDto.getReservationId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        reservation.setStatus(statusUpdateDto.getStatus());

        return mapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public void visit(VisitCheckDto visitCheckDto) {
        Store store = storeRepository.findById(visitCheckDto.getStoreId())
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        if (store.getVerificationCode().equals(visitCheckDto.getCode())) {
            Reservation reservation = reservationRepository
                    .findById(visitCheckDto.getReservationId())
                    .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

            visitTimeCheck(reservation.getReservationTime(), visitCheckDto.getVisitTime());
            reservation.setStatus(ReservationStatus.VISIT);
            reservationRepository.save(reservation);

        } else {
            throw new CustomException(ErrorCode.CODE_MISMATCH);
        }

    }

    /**
     * 예약시간 10분 전을 기준으로 그 이후에 도착하는 경우 체크인 불가
     * 12시 30분 예약인 경우 12시 20분 59초까지 가능
     */
    void visitTimeCheck(LocalDateTime reservationTime, LocalDateTime visitTime) {
        LocalDateTime safeTime = reservationTime.minusMinutes(9).minusSeconds(1);
        if (visitTime.isAfter(safeTime)) {
            throw new CustomException(ErrorCode.NOT_CHECK_IN_TIME);
        }
    }


}
