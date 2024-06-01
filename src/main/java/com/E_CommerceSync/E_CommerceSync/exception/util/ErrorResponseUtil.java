package com.E_CommerceSync.E_CommerceSync.exception.util;


import com.E_CommerceSync.E_CommerceSync.exception.dto.ErrorResponseDto;
import com.E_CommerceSync.E_CommerceSync.exception.dto.InvalidParameterDto;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponseUtil {

    private ErrorResponseUtil() {}

    public static ErrorResponseDto build(final String code, final String message, final HttpStatus status) {
        return buildDetails(code, message, status);
    }

    public static ErrorResponseDto build(final String code, final String message, final HttpStatus status,
                                         final List<InvalidParameterDto> invalidParameters) {
        return buildDetails(code, message, status, invalidParameters);
    }

    private static ErrorResponseDto buildDetails(final String code, final String message, final HttpStatus status) {
        return buildDetails(code, message, status, null);
    }

    private static ErrorResponseDto buildDetails(final String code, final String message, final HttpStatus status,
                                                 final List<InvalidParameterDto> invalidParameters) {
        return new ErrorResponseDto(code,message, status.value(), LocalDateTime.now(), invalidParameters);
    }
}
