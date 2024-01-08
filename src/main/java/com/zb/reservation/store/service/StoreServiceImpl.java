package com.zb.reservation.store.service;

import com.zb.reservation.exception.CustomException;
import com.zb.reservation.exception.ErrorCode;
import com.zb.reservation.store.dto.LocationDto;
import com.zb.reservation.store.dto.StoreDetailDto;
import com.zb.reservation.store.dto.StoreDto;
import com.zb.reservation.store.entity.Store;
import com.zb.reservation.store.mapper.StoreMapper;
import com.zb.reservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Override
    public List<StoreDto> orderByAlphabet(LocationDto userLocation) {
        List<Object[]> storeWithDistance =
                storeRepository.findAllOrderByStoreName(userLocation.getLnt(),
                        userLocation.getLat());

        return mapping(storeWithDistance);
    }

    @Override
    public List<StoreDto> orderByScore(LocationDto userLocation) {
        List<Object[]> storeWithDistance =
                storeRepository.findAllOrderByScore(userLocation.getLnt(),
                        userLocation.getLat());

        return mapping(storeWithDistance);
    }

    @Override
    public List<StoreDto> orderByDistance(LocationDto userLocation) {
        List<Object[]> storeWithDistance =
                storeRepository.findAllOrderByDistance(userLocation.getLnt(),
                        userLocation.getLat());

        return mapping(storeWithDistance);
    }

    @Override
    public List<StoreDto> search(String keyword, LocationDto locationDto) {
        List<Object[]> storeWithDistance =
                storeRepository.findAllByStoreNameContaining(
                        keyword,
                        locationDto.getLnt(),
                        locationDto.getLat());

        return mapping(storeWithDistance);
    }

    /**
     * 테이블에서 Object 배열로 받아온 데이터를 storeDto로 매핑
     * distance를 받아오기 위해서 Object배열로 받음
     */
    private List<StoreDto> mapping(List<Object[]> storeWithDistance) {
        return storeWithDistance.stream()
                .map(this::arrayToDto)
                .collect(Collectors.toList());
    }

    private StoreDto arrayToDto(Object[] store) {
        if (ObjectUtils.isEmpty(store) || store.length < 6) {
            return null;
        }
        return StoreDto.builder()
                .id(((BigInteger) store[0]).longValue())
                .storeName((String) store[1])
                .address((String) store[2])
                .description((String) store[3])
                .score((double) store[4])
                .distance((double) store[5])
                .build();
    }

    @Override
    public StoreDetailDto detail(long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        return storeMapper.toDto(store);
    }

}
