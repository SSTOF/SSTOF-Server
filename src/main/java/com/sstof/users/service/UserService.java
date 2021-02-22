package com.sstof.users.service;

import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRepository;
import com.sstof.users.dto.UserCreateRequestDto;
import com.sstof.users.dto.UserInfoResponseDto;
import com.sstof.users.dto.UserLoginRequestDto;
import com.sstof.users.dto.UserLoginResponseDto;
import com.sstof.users.exception.UserEmailConflictException;
import com.sstof.users.exception.UserLoginFailedException;
import com.sstof.auth.tools.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserInfoResponseDto save(UserCreateRequestDto dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserEmailConflictException();
        }
        User user = User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
       return new UserInfoResponseDto(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserLoginResponseDto signIn(UserLoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            return UserLoginResponseDto.builder()
                    .userId(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .created_at(user.getCreatedAt())
                    .role(user.getRole())
                    .accessToken(JwtTokenUtil.generateAccessToken(user))
                    .refreshToken(JwtTokenUtil.generateRefreshToken(user.getId(), user.getRole()))
                    .build();
        } else throw new UserLoginFailedException();
    }
}
