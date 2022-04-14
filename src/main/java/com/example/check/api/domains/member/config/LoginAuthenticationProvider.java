package com.example.check.api.domains.member.config;

import com.example.check.api.domains.member.dto.ResponseLoginDto;
import com.example.check.api.domains.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Log4j2
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    private static final String AUTHENTICATE_DEFAULT_MESSAGE = "아이디 또는 비밀번호가 다릅니다.";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 인증 처리 이후의 인증토큰.
        String email = authentication.getName();

        String password = (String) authentication.getCredentials();

        ResponseLoginDto responseLoginDto = (ResponseLoginDto) memberDetailsService.loadUserByUsername(email);

        if(!checkPassword(password, responseLoginDto.getPassword())) {
            throw new BadCredentialsException(AUTHENTICATE_DEFAULT_MESSAGE);
        }

        return new UsernamePasswordAuthenticationToken(responseLoginDto, password, responseLoginDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private boolean checkPassword(String inputPassword, String originalPassword) {
        return passwordEncoder.matches(inputPassword, originalPassword);
    }
}
