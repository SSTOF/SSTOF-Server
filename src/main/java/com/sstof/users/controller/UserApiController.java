package com.sstof.users.controller;

import com.sstof.users.dto.UserCreateRequestDto;
import com.sstof.users.dto.UserInfoResponseDto;
import com.sstof.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/v1/user")
    @ResponseStatus(HttpStatus.CREATED)
    public UserInfoResponseDto save(@Valid @RequestBody UserCreateRequestDto dto) {
        return userService.save(dto);
    }
}
