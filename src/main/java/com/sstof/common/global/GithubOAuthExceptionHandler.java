package com.sstof.common.global;

import com.sstof.auth.exception.GithubException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class GithubOAuthExceptionHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof GithubException) {
            GithubException githubException =  (GithubException) exception;
            response.setStatus(githubException.getStatus());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");
            response.sendRedirect("http://localhost:3000/signup?error_code=" + githubException.getStatus());
        }
    }
}
