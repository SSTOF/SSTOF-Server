package com.sstof.users.exception;

import com.sstof.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    private static final String MESSAGE = "조회된 사용자가 없습니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
