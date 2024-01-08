package com.zb.reservation.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoreDto {
    private long id;

    private String storeName;

    private String address;

    private String description;

    private double score;

    private double distance;

}
