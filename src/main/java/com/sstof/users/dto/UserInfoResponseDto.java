package com.sstof.users.dto;

import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserInfoResponseDto {
    private Integer userId;
    private String name;
    private String email;
    private LocalDateTime created_at;
    private UserRole role;

    public UserInfoResponseDto(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.created_at = user.getCreated_at();
        this.role = user.getRole();
    }
}
