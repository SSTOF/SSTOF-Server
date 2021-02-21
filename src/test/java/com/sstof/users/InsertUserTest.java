package com.sstof.users;

import com.sstof.ApiIntegrationTest;
import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRole;
import com.sstof.users.dto.UserCreateRequestDto;
import com.sstof.users.dto.UserInfoResponseDto;
import com.sstof.users.exception.UserNotFoundException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InsertUserTest extends ApiIntegrationTest {

    private static final String EMAIL = "test@test.com";
    private static final String NAME = "NAME";
    private static final String PASSWORD = "PasswordPasswordPasswordPasswordPasswordPasswordPasswordPassword";
    @Test
    public void insert_responseIsOk() {
        UserCreateRequestDto requestDto = new UserCreateRequestDto();
        requestDto.setName(NAME);
        requestDto.setEmail(EMAIL);
        requestDto.setPassword(PASSWORD);

        RequestEntity<UserCreateRequestDto> request = RequestEntity.post(URI.create("/v1/user"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);

        ResponseEntity<UserInfoResponseDto> response = restTemplate.exchange(request, UserInfoResponseDto.class);
        User savedUser = userRepository.findByEmail(response.getBody().getEmail()).orElseThrow(() -> new UserNotFoundException("해당 이메일로 조회된 사용자가 없습니다."));
        Integer userId = savedUser.getId();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        UserInfoResponseDto responseBody = response.getBody();

        assertEquals(responseBody.getUserId(), userId);
        assertEquals(responseBody.getEmail(), EMAIL);
        assertEquals(responseBody.getName(), NAME);
        assertTrue(responseBody.getCreated_at().isBefore(LocalDateTime.now()));
        assertEquals(responseBody.getRole(), UserRole.USER);
    }
}
