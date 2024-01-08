package com.zb.reservation.store.service;

import com.zb.reservation.exception.CustomException;
import com.zb.reservation.exception.ErrorCode;
import com.zb.reservation.store.dto.StoreManagement;
import com.zb.reservation.store.entity.Store;
import com.zb.reservation.store.mapper.StoreMapper;
import com.zb.reservation.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreManagementServiceImpl implements StoreManagementService {
    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper = StoreMapper.INSTANCE;

    @Override
    public void addStore(StoreManagement.addDto request) {
        Optional<Store> optionalStore =
                storeRepository.findByStoreName(request.getStoreName());

        if (optionalStore.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_REGISTERED_STORE);
        }

        String verificationCode = generateCode();

        storeRepository.save(storeMapper.toEntity(request, verificationCode));

    }

    @Override
    public void editStore(StoreManagement.editDto request) {
        Store store = storeRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        storeMapper.updateFromDto(request, store);

        storeRepository.save(store);
    }

    @Override
    public void deleteStore(long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        storeRepository.delete(store);
    }


    @Override
    public String updateVerificationCode(long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new CustomException(ErrorCode.STORE_NOT_FOUND));

        String newCode = generateCode();
        store.setVerificationCode(newCode);
        storeRepository.save(store);

        return newCode;
    }

    /**
     * 숫자 + 문자 조합의 6자리 난수 생성
     */
    private String generateCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;

        StringBuilder code = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(characters.length());
            code.append(characters.charAt(idx));
        }

        return code.toString();
    }
}
