package com.sstof.common.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sstof.auth.exception.GithubEmailNotPublicException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class GithubOAuthExceptionHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof GithubEmailNotPublicException) {
            GithubEmailNotPublicException githubEmailNotPublicException = (GithubEmailNotPublicException) exception;
            response.setStatus(githubEmailNotPublicException.getSTATUS());
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", LocalDateTime.now());
            data.put("message", githubEmailNotPublicException.getMessage());
            data.put("exception", githubEmailNotPublicException.getMessage());

            response.getOutputStream().println(objectMapper.writeValueAsString(data));
        }
    }
}
