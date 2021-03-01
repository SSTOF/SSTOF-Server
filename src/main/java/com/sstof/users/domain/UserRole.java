package com.sstof.users.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    USER("ROLE_USER", "user"),
    ADMIN("ROLE_ADMIN", "admin");
    private final String key;
    private final String title;
}
