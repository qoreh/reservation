package com.zb.reservation.store.service;

import com.zb.reservation.store.dto.StoreManagement;

public interface StoreManagementService {
    /**
     * 상점 추가
     */
    void addStore(StoreManagement.addDto request);

    /**
     * 상점 수정
     */
    void editStore(StoreManagement.editDto request);

    /**
     * 상점 삭제
     */
    void deleteStore(long storeId);

    /**
     * 방문 확인 코드 발급
     */
    String updateVerificationCode(long storeId);
}
