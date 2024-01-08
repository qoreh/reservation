package com.zb.reservation.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String field;
    private String message;

    public static ErrorResponse of(FieldError e) {
        return ErrorResponse.builder()
                .field(e.getField())
                .message(e.getDefaultMessage())
                .build();
    }
}
