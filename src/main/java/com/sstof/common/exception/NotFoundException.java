package com.sstof.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException{
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
    public NotFoundException() {
        this("Not Found Exception.");
    }
}
