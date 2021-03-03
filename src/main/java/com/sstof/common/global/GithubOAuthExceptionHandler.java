package com.sstof.common.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sstof.auth.exception.GithubException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class GithubOAuthExceptionHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("HANDLE!");
        if(exception instanceof GithubException) {
            GithubException githubException =  (GithubException) exception;
            response.setStatus(githubException.getStatus());
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", LocalDateTime.now());
            data.put("message", githubException.getMessage());
            data.put("exception", githubException.getMessage());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");

            response.getWriter().println(objectMapper.writeValueAsString(data));
        }
    }
}
