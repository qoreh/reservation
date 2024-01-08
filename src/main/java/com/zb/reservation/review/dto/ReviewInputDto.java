package com.zb.reservation.review.dto;

import lombok.Data;


public class ReviewInputDto {

    @Data
    public static class Add {
        private long reservationId;

        private long storeId;

        private String userId;

        private String reviewText;

        private double score;
    }

    @Data
    public static class Edit {
        private long id;

        private String reviewText;

        private double score;
    }


}
