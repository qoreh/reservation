package com.zb.reservation.store.controller;

import com.zb.reservation.user.dto.ErrorResponse;
import com.zb.reservation.store.dto.StoreManagement;
import com.zb.reservation.store.service.StoreManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreManagementController {

    private final StoreManagementService storeManagementService;

    /**
     * 상점 추가
     */
    @PostMapping("/manager/store/add")
    public ResponseEntity<?> addStore(
            @RequestBody @Valid StoreManagement.addDto request, Errors errors
    ) {
        if (errors.hasErrors()) {
            return checkError(errors);
        }

        storeManagementService.addStore(request);

        return ResponseEntity.ok().build();
    }

    /**
     * 상점 수정
     */
    @PostMapping("/manager/store/edit")
    public ResponseEntity<?> editStore(
            @RequestBody @Valid StoreManagement.editDto request, Errors errors
    ) {
        if (errors.hasErrors()) {
            return checkError(errors);
        }

        storeManagementService.editStore(request);

        return ResponseEntity.ok().build();
    }

    /**
     * 상점 삭제
     */
    @PostMapping("/manager/store/remove")
    public ResponseEntity<?> removeStore(@RequestParam long storeId) {

        storeManagementService.deleteStore(storeId);

        return ResponseEntity.ok().build();
    }

    /**
     * 도착 확인 처리를 상점 내 키오스크에 띄워진 인증 번호를 유저의 인증 창에 입력하는 방식으로 진행
     * 최초 상점 등록 시에는 인증번호가 자동 생성되어 등록, 추후 새로 발급을 원할 시에 이용할 수 있도록 함
     */
    @PostMapping("/manager/store/codeUpdate")
    public ResponseEntity<?> codeUpdate(@RequestParam long storeId) {
        String newCode = storeManagementService.updateVerificationCode(storeId);

        return new ResponseEntity<>(newCode, HttpStatus.OK);
    }

    /**
     * 입력받은 값에 대한 오류 체크
     */
    public ResponseEntity<?> checkError(Errors errors) {
        List<ErrorResponse> responseList = new ArrayList<>();
        errors.getAllErrors().forEach((e) -> {
            responseList.add(ErrorResponse.of((FieldError) e));
        });

        return new ResponseEntity<>(responseList, HttpStatus.BAD_REQUEST);
    }

}
