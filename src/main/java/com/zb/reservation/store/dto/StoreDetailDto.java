package com.zb.reservation.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreDetailDto {
    private long id;

    private String storeName;

    private String address;

    private String description;

    private double score;
}
