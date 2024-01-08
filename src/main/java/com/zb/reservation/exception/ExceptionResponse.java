package com.zb.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {

    private ErrorCode errorCode;
    private String errorMessage;
}
