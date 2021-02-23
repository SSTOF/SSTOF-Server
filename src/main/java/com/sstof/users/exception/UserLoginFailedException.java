package com.sstof.users.exception;

import com.sstof.common.exception.NotFoundException;

public class UserLoginFailedException extends NotFoundException {
    private static final String MESSAGE = "이메일 또는 비밀번호가 잘못되었습니다.";
    public UserLoginFailedException() {
        super(MESSAGE);
    }
}
