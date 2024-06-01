package com.E_CommerceSync.E_CommerceSync.exception.policy;

import org.springframework.http.HttpStatus;

public interface ExceptionPolicy {
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
