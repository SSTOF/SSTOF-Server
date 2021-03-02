package com.sstof.common.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sstof.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleApiExceptions(Exception exception, WebRequest webRequest) {
        if(exception instanceof ApiException) {
            ApiException apiException = (ApiException) exception;
            return handleExceptionInternal(exception, null, new HttpHeaders(), apiException.getHttpStatus(), webRequest);
        } else {
            return handleExceptionInternal(exception, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
        }
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
