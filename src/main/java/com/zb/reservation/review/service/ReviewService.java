package com.zb.reservation.review.service;

import com.zb.reservation.review.dto.ReviewInputDto;

public interface ReviewService {

    /**
     * 리뷰 작성
     */
    void addReview(ReviewInputDto.Add reviewInputDto);

    /**
     * 리뷰 수정
     */
    void editReview(ReviewInputDto.Edit reviewInputDto);

    /**
     * 리뷰 삭제
     */
    void deleteReview(long reviewId);
}
