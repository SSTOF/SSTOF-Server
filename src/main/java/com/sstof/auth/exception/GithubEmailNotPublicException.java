package com.sstof.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class GithubEmailNotPublicException extends AuthenticationException {

    private static final String MESSAGE = "깃허브 링크에 Public Email이 없습니다. 설정 후 다시 시도해 주세요.";

    public GithubEmailNotPublicException() {
        super(MESSAGE);
    }
}
