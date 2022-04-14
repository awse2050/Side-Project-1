package com.example.check.api.domains.member.config;

import com.example.check.api.domains.member.dto.AttemptLoginDto;
import com.example.check.api.domains.member.dto.ResponseLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String FILTER_PROCESSES_URL = "/v1/member/login";
    private static final String USERNAME_PARAMETER = "email";
    private static final String PASSWORD_PARAMETER = "password";

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl(FILTER_PROCESSES_URL);
        setUsernameParameter(USERNAME_PARAMETER);
        setPasswordParameter(PASSWORD_PARAMETER);
    }

    /*
        로그인시 가장 먼저 호출된다.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            AttemptLoginDto attemptLoginDto = new ObjectMapper().readValue(request.getInputStream(), AttemptLoginDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            attemptLoginDto.getEmail(),
                            attemptLoginDto.getPassword()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 인증이 성공되면 해당 메서드 호출
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        log.info("success Login...");
        log.info(((ResponseLoginDto) authResult.getPrincipal()));
        log.info(((ResponseLoginDto) authResult.getPrincipal()).getEmail());

    }
}
