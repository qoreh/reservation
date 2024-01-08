package com.zb.reservation.store.service;

import com.zb.reservation.store.dto.LocationDto;
import com.zb.reservation.store.dto.StoreDetailDto;
import com.zb.reservation.store.dto.StoreDto;

import java.util.List;

public interface StoreService {
    // 가나다순 상점
    List<StoreDto> orderByAlphabet(LocationDto userLocation);

    // 별점순
    List<StoreDto> orderByScore(LocationDto userLocation);

    // 거리순
    List<StoreDto> orderByDistance(LocationDto userLocation);

    // 검색
    List<StoreDto> search(String keyword, LocationDto locationDto);

    // 상세 정보
    StoreDetailDto detail(long id);
}
