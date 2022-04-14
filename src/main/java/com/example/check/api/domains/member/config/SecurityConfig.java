package com.example.check.api.domains.member.config;

import com.example.check.api.domains.member.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberDetailsService memberDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                    .antMatchers("/h2-console/**")
                        .permitAll()
                    .antMatchers("/v1/**")
                        .permitAll()
                .and()
                    .headers()
                        .frameOptions().sameOrigin() // H2 Page XFrame Error
                .and()
                    .formLogin().permitAll()
                .and()
                    .logout()
                .and()
                    .csrf().disable();

        http.addFilter(jwtAuthenticationFilter());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginAuthenticationProvider loginAuthenticationProvider() {
        return new LoginAuthenticationProvider(memberDetailsService, passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }
}
