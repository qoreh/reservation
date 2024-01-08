package com.zb.reservation.review.service;

import com.zb.reservation.exception.CustomException;
import com.zb.reservation.exception.ErrorCode;
import com.zb.reservation.reservation.entity.Reservation;
import com.zb.reservation.reservation.repository.ReservationRepository;
import com.zb.reservation.reservation.type.ReservationStatus;
import com.zb.reservation.review.mapper.ReviewMapper;
import com.zb.reservation.review.dto.ReviewInputDto;
import com.zb.reservation.review.entity.Review;
import com.zb.reservation.review.repository.ReviewRepository;
import com.zb.reservation.store.entity.Store;
import com.zb.reservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;
    private final ReviewMapper reviewMapper = ReviewMapper.INSTANCE;

    @Override
    public void addReview(ReviewInputDto.Add reviewInputDto) {
        Optional<Review> optionalReview = reviewRepository
                .findByReservationId(reviewInputDto.getReservationId());

        if (optionalReview.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_WRITTEN_REVIEW);
        }

        Reservation reservation = reservationRepository
                .findById(reviewInputDto.getReservationId())
                .orElseThrow(() -> new CustomException(ErrorCode.RESERVATION_NOT_FOUND));

        // 리뷰를 등록하려는 store와 예약한 store가 같은지 확인
        if (reservation.getStoreId() != reviewInputDto.getStoreId()) {
            throw new CustomException(ErrorCode.INCORRECT_REQUEST);
        }

        // 방문까지 완료한 상태인지 확인
        checkStatus(reservation.getStatus());

        reviewRepository.save(reviewMapper.toEntity(reviewInputDto));

        // 리뷰 저장 후, 해당 상점의 별점 업데이트
        updateStoreScore(reviewInputDto.getStoreId());
    }

    private void updateStoreScore(long storeId) {
        double scoreAvg = reviewRepository
                .findScoreAvgByStoreId(storeId);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        store.setScore(scoreAvg);

        storeRepository.save(store);
    }

    private void checkStatus(ReservationStatus status) {
        if (status == ReservationStatus.REFUSE || status == ReservationStatus.CANCEL) {
            throw new CustomException(ErrorCode.CANCELED_RESERVATION);
        } else if (status == ReservationStatus.APPROVAL
                || status == ReservationStatus.REQUEST) {
            throw new CustomException(ErrorCode.NOT_VISITED_RESERVATION);
        }
    }

    @Override
    public void editReview(ReviewInputDto.Edit reviewInputDto) {
        Review review = reviewRepository.findById(reviewInputDto.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

        reviewMapper.updateEntity(reviewInputDto, review);

        reviewRepository.save(review);

        updateStoreScore(review.getStoreId());
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }


}
