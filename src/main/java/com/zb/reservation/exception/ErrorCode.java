package com.zb.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_ID_NOT_FOUND("존재하지 않는 아이디 입니다."),
    USER_NOT_FOUND("회원정보를 찾을 수 없습니다."),
    PASSWORD_MISMATCH("잘못된 비밀번호 입니다."),
    ALREADY_EXIST_ID("이미 존재하는 아이디 입니다."),
    ALREADY_EXIST_PHONE("해당 번호로 가입된 정보가 존재합니다."),
    ALREADY_REGISTERED_STORE("이미 등록된 상점입니다."),
    STORE_NOT_FOUND("존재하지 않는 상점입니다"),
    RESERVATION_NOT_FOUND("예약정보가 존재하지 않습니다"),
    CODE_MISMATCH("코드가 일치하지 않습니다."),
    ALREADY_WRITTEN_REVIEW("이미 리뷰가 작성된 예약입니다."),
    INCORRECT_REQUEST("잘못된 요청입니다."),
    CANCELED_RESERVATION("취소된 예약입니다."),
    NOT_VISITED_RESERVATION("방문이 확인되지 않은 예약입니다."),
    REVIEW_NOT_FOUND("리뷰를 찾을 수 없습니다."),
    NOT_CHECK_IN_TIME("체크인 시간이 아닙니다.");


    private final String description;
}
