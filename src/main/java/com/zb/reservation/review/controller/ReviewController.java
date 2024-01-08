package com.zb.reservation.review.controller;

import com.zb.reservation.review.dto.ReviewInputDto;
import com.zb.reservation.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 작성
     */
    @PostMapping("/review/write")
    public ResponseEntity<?> addReview(@RequestBody ReviewInputDto.Add reviewDto) {
        reviewService.addReview(reviewDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 리뷰 수정
     */
    @PostMapping("/review/edit")
    public ResponseEntity<?> editReview(@RequestBody ReviewInputDto.Edit reviewInputDto) {
        reviewService.editReview(reviewInputDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 리뷰 삭제
     */
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable long reviewId) {
        reviewService.deleteReview(reviewId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
