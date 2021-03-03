package com.sstof.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GithubEmailInUseException extends AuthenticateException{
    private final Integer STATUS = HttpStatus.CONFLICT.value();

    public GithubEmailInUseException(String message) {
        super(message);
    }
}
