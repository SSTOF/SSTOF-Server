package com.sstof.auth.exception;

import com.sstof.common.exception.UnauthorizedException;

public class AuthenticateException extends UnauthorizedException {
    public AuthenticateException(String message) {
        super(message);
    }
}
