package com.sstof.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequestDto {

    @NotBlank(message = "Name field is required.")
    private String name;

    @Email
    @NotBlank(message = "Email field is required.")
    private String email;

    @NotBlank(message = "Password field is required.")
    @Length(min = 64, max = 64)
    private String password;
}
