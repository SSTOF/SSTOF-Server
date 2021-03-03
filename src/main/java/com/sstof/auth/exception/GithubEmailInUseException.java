package com.sstof.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class GithubEmailInUseException extends AuthenticationException{
    private final Integer STATUS = HttpStatus.CONFLICT.value();

    public GithubEmailInUseException(String message) {
        super(message);
    }
}
