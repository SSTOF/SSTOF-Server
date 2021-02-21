package com.sstof.users.service;

import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRepository;
import com.sstof.users.dto.UserCreateRequestDto;
import com.sstof.users.dto.UserInfoResponseDto;
import com.sstof.users.exception.UserEmailConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
