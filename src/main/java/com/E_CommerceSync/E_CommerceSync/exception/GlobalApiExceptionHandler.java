package com.E_CommerceSync.E_CommerceSync.exception;


import com.E_CommerceSync.E_CommerceSync.exception.dto.ErrorResponseDto;
import com.E_CommerceSync.E_CommerceSync.exception.dto.InvalidParameterDto;
import com.E_CommerceSync.E_CommerceSync.exception.util.ErrorResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        String errorMessage = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));

        String actualErrorMessage = Optional
                .ofNullable(ex.getMessage())
                .map(message -> String.join("\n", message, errorMessage))
                .orElse(errorMessage);

        ErrorResponseDto error = new ErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                actualErrorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                null);

        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(BusinessException ex, ServletWebRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(ex.httpStatus).body(fillExceptionDto(ex, ex.getHttpStatus()));
    }

    @ExceptionHandler(NoRollBackBusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleNoRollBackForBusinessException(NoRollBackBusinessException ex, ServletWebRequest request) {
        ex.printStackTrace();
        return ResponseEntity.status(ex.httpStatus).body(fillExceptionDto(ex, ex.getHttpStatus()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        log(ex, (ServletWebRequest) request);
        final List<InvalidParameterDto> invalidParameters = ex
                .getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> new InvalidParameterDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        final ErrorResponseDto errorResponseDto = ErrorResponseUtil.build(
                MethodArgumentNotValidException.class.getSimpleName(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST, invalidParameters);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    private void log(final Exception ex, final ServletWebRequest request) {
        final Optional<HttpMethod> httpMethod;
        final Optional<String> requestUrl;

        final Optional<ServletWebRequest> possibleIncomingNullRequest = Optional.ofNullable(request);
        if (possibleIncomingNullRequest.isPresent()) {
            // get the HTTP Method
            httpMethod = Optional.of(possibleIncomingNullRequest.get().getHttpMethod());
            requestUrl = Optional.of(possibleIncomingNullRequest.get().getRequest().getRequestURL().toString());
        } else {
            httpMethod = Optional.empty();
            requestUrl = Optional.empty();
        }

        log.error("Request {} {} failed with exception reason: {}", (httpMethod.isPresent() ? httpMethod.get() : "'null'"),
                (requestUrl.orElse("'null'")), ex.getMessage(), ex);
    }

    private <E extends RuntimeException> ErrorResponseDto fillExceptionDto(E ex, HttpStatus status) {
        return new ErrorResponseDto(
                        status.toString(),
                        ex.getMessage(),
                        status.value(),
                        LocalDateTime.now(),
                        null);
    }
}
