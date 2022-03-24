package com.example.check.api.domains.member.config;

import com.example.check.api.domains.member.dto.LoginCheckDto;
import com.example.check.api.domains.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    private static final String AUTHENTICATE_DEFAULT_MESSAGE = "아이디 또는 비밀번호가 다릅니다.";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        LoginCheckDto loginCheckDto = (LoginCheckDto) memberDetailsService.loadUserByUsername(email);

        if(!checkPassword(password, loginCheckDto.getPassword())) {
            throw new BadCredentialsException(AUTHENTICATE_DEFAULT_MESSAGE);
        }

        return new UsernamePasswordAuthenticationToken(loginCheckDto, password, loginCheckDto.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private boolean checkPassword(String inputPassword, String originalPassword) {
        return passwordEncoder.matches(inputPassword, originalPassword);
    }
}
