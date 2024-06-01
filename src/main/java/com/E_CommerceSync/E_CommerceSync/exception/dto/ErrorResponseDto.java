package com.E_CommerceSync.E_CommerceSync.exception.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDto(
        String code,
        String message,
        Integer status,
        LocalDateTime timestamp,
        List<InvalidParameterDto> invalidParameter
) {
    public String toJson() {
       return String.format("""
               {
                  "code": "%s",
                  "message": "%s",
                  "status": %d,
                  "timestamp": "%s",
               }
               """, code, message, status, timestamp);
    }
}
