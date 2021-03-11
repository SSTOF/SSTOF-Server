package com.sstof.common.global;

import com.sstof.auth.exception.GithubException;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class GithubOAuthExceptionHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof GithubException) {
            GithubException githubException =  (GithubException) exception;
            response.setStatus(githubException.getStatus());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF8");
            // TODO : 아래의 sendRedirect location 값 변경(master branch merge 시)
            response.sendRedirect("http://http://dev.ssutackoverflow.com.s3-website.ap-northeast-2.amazonaws.com/signup?error_code=" + githubException.getStatus());
        }
    }
}
