package com.sstof.users;

import com.sstof.ApiIntegrationTest;
import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRole;
import com.sstof.users.dto.UserLoginRequestDto;
import com.sstof.users.dto.UserLoginResponseDto;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class UserLoginTest extends ApiIntegrationTest {

    private User createUser() {
        return userRepository.save(User.builder()
        .name(NAME).email(EMAIL).password(PASSWORD).build());
    }

    private UserLoginRequestDto getUserLoginRequestDto(String email, String password) {
        UserLoginRequestDto dto = new UserLoginRequestDto();
        dto.setEmail(email);
        dto.setPassword(password);
        return dto;
    }

    @Test
    public void login_responseIsOk() {
        User savedUser = createUser();

        UserLoginRequestDto requestDto = getUserLoginRequestDto(EMAIL, PASSWORD);

        RequestEntity<UserLoginRequestDto> request = RequestEntity.post(URI.create("/v1/user/sign-in"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);

        ResponseEntity<UserLoginResponseDto> response = restTemplate.exchange(request, UserLoginResponseDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser.getId(), response.getBody().getUserId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertTrue(response.getBody().getCreated_at().isBefore(LocalDateTime.now()));
        assertEquals(UserRole.USER, response.getBody().getRole());
        assertNotNull(response.getBody().getAccessToken());
        assertNotNull(response.getBody().getRefreshToken());
    }

    @Test
    public void login_responseIsNotFound_ifEmailIsWrong() {
        UserLoginRequestDto requestDto = getUserLoginRequestDto("wrong@email.com", PASSWORD);
        RequestEntity<UserLoginRequestDto> request = RequestEntity.post(URI.create("/v1/user/sign-in"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void login_responseIsNotFound_ifPasswordIsWrong() {
        UserLoginRequestDto requestDto = getUserLoginRequestDto(EMAIL, "PassPassPassPassPassPassPassPassPassPassPassPassPassPassPassPass");
        RequestEntity<UserLoginRequestDto> request = RequestEntity.post(URI.create("/v1/user/sign-in"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void login_responseIsBadRequest_ifEmailIsMalformed() {
        UserLoginRequestDto requestDto = getUserLoginRequestDto("notFormOfEmail", PASSWORD);
        RequestEntity<UserLoginRequestDto> request = RequestEntity.post(URI.create("/v1/user/sign-in"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void login_responseIsBadRequest_ifPasswordLengthIsNot64() {
        UserLoginRequestDto requestDto = getUserLoginRequestDto(EMAIL, "notLengthOf64");
        RequestEntity<UserLoginRequestDto> request = RequestEntity.post(URI.create("/v1/user/sign-in"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDto);
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
