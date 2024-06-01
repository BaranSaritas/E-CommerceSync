package com.E_CommerceSync.E_CommerceSync.exception;


import com.E_CommerceSync.E_CommerceSync.exception.policy.BusinessExceptionPolicy;
import com.E_CommerceSync.E_CommerceSync.exception.reasons.BusinessExceptionReason;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;

@Getter
@Setter
public class NoRollBackBusinessException extends RuntimeException implements BusinessExceptionPolicy {

    protected final String code;
    protected final String message;
    protected final HttpStatus httpStatus;

    public NoRollBackBusinessException(final BusinessExceptionReason reason) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
        this.httpStatus = reason.getHttpStatus();
    }

    public NoRollBackBusinessException(final BusinessExceptionReason reason, final HttpStatus overridingHttpStatus) {
        this.code = reason.getCode();
        this.message = reason.getMessage();
        this.httpStatus = overridingHttpStatus;
    }

    public NoRollBackBusinessException(final BusinessExceptionReason reason, final Object... parameters) {
        if (parameters != null) {
            this.message = format(reason.getMessage(), parameters);
        } else {
            this.message = reason.getMessage();
        }
        this.code = reason.getCode();
        this.httpStatus = reason.getHttpStatus();
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    @Override
    public String toString() {
        return format("BusinessException(code=%s, message=%s, httpStatus=%s)", this.getCode(), this.getMessage(),
                this.getHttpStatus().value());
    }
}
