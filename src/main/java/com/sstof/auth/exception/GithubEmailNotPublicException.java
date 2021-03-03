package com.sstof.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GithubEmailNotPublicException extends GithubException {

    private static final Integer STATUS = HttpStatus.BAD_REQUEST.value();
    public GithubEmailNotPublicException(String message) {
        super(STATUS, message);
    }
}
