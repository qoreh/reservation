package com.zb.reservation.store.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


public class StoreManagement {

    @Data
    public static class addDto {
        @NotBlank
        private String storeName;

        @NotBlank
        private String address;

        private String description;

        private double lnt;

        private double lat;

        private int capacity;

        private String verificationCode;

    }

    @Data
    public static class editDto {

        private long id;

        @NotBlank
        private String storeName;

        @NotBlank
        private String address;

        private String description;

        private double score;

        private double lat;

        private double lnt;

        private int capacity;
    }
}
