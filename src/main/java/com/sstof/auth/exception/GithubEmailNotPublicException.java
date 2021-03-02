package com.sstof.auth.exception;

import com.sstof.common.exception.BadRequestException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;

public class GithubEmailNotPublicException extends OAuth2AuthorizationException {

    private static final String MESSAGE = "깃허브 링크에 Public Email이 없습니다. 설정 후 다시 시도해 주세요.";

    public GithubEmailNotPublicException() {
        super(new OAuth2Error(MESSAGE));
    }
}
