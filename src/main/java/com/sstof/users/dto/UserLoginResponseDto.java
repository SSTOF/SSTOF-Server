package com.sstof.users.dto;

import com.sstof.users.domain.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Integer userId;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private UserRole role;
    private String accessToken;
    private String refreshToken;

    @Builder
    public UserLoginResponseDto(Integer userId, String name, String email, LocalDateTime created_at, UserRole role, String accessToken, String refreshToken) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.role = role;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
