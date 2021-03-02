package com.sstof.auth.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

@Getter
public class GithubEmailNotPublicException extends AuthenticationException {

    private final String MESSAGE = "깃허브 링크에 Public Email이 없습니다. 설정 후 다시 시도해 주세요.";
    private final Integer STATUS = HttpStatus.BAD_REQUEST.value();

    public GithubEmailNotPublicException(String message) {
        super(message);
    }
}
