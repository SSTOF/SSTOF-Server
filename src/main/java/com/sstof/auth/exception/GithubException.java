package com.sstof.auth.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public abstract class GithubException extends AuthenticationException {
    protected Integer status;
    protected String message;

    public GithubException(Integer status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
