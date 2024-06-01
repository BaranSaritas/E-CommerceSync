package com.E_CommerceSync.E_CommerceSync.exception.reasons;

import com.E_CommerceSync.E_CommerceSync.exception.policy.BusinessExceptionPolicy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessExceptionReason implements BusinessExceptionPolicy {

    EMAIL_ALREADY_EXISTS("Email already exists", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND_BY_EMAIL("User not found by email", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND_BY_ID("User not found by id", HttpStatus.NOT_FOUND),
    EMAIL_IS_NOT_VERIFIED("Email is not verified", HttpStatus.BAD_REQUEST),
    USER_ALREADY_VERIFIED("User already verified", HttpStatus.BAD_REQUEST),
    ADMIN_NOT_FOUND_BY_EMAIL("Admin not found by email", HttpStatus.NOT_FOUND),
    ADMIN_NOT_FOUND_BY_ID("Admin not found by id", HttpStatus.NOT_FOUND),
    ADMIN_PASSWORD_IS_NOT_CORRECT("Admin password is not correct", HttpStatus.BAD_REQUEST);


    private final String code = this.name(); //BusinessExceptionReason.class.getSimpleName();
    private final String message;
    private final HttpStatus httpStatus;
}
