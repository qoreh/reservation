package com.zb.reservation.store.controller;

import com.zb.reservation.store.dto.LocationDto;
import com.zb.reservation.store.dto.StoreDetailDto;
import com.zb.reservation.store.dto.StoreDto;
import com.zb.reservation.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 가나다순 정렬
     */
    @GetMapping("/store/list/alphabet")
    public ResponseEntity<?> orderByAlphabet(@RequestBody LocationDto userLocation) {

        List<StoreDto> storeList = storeService.orderByAlphabet(userLocation);

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    /**
     * 별점순
     */
    @GetMapping("/store/list/score")
    public ResponseEntity<?> orderByScore(@RequestBody LocationDto userLocation) {

        List<StoreDto> storeDtoList = storeService.orderByScore(userLocation);

        return new ResponseEntity<>(storeDtoList, HttpStatus.OK);
    }

    /**
     * 거리순
     */
    @GetMapping("/store/list/distance")
    public ResponseEntity<?> orderByDistance(@RequestBody LocationDto userLocation) {

        List<StoreDto> storeList = storeService.orderByDistance(userLocation);

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    /**
     * 검색
     */
    @GetMapping("/store/list/search")
    public ResponseEntity<?> search(@RequestParam String keyword,
                                    @RequestBody LocationDto locationDto) {

        List<StoreDto> storeList = storeService.search(keyword, locationDto);

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    /**
     * 상세 정보
     */
    @GetMapping("/store/detail")
    public ResponseEntity<?> detail(@RequestParam long id) {
        StoreDetailDto storeDetail = storeService.detail(id);

        return new ResponseEntity<>(storeDetail, HttpStatus.OK);
    }

}
