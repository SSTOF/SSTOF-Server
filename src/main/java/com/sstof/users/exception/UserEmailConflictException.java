package com.sstof.users.exception;

import com.sstof.common.exception.ConflictException;

public class UserEmailConflictException extends ConflictException {
    private static final String MESSAGE = "해당 이메일은 이미 사용 중입니다.";
    public UserEmailConflictException() {
        super(MESSAGE);
    }
}
