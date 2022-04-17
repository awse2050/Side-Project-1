package com.example.check.api.config;

import com.example.check.api.domains.member.config.JwtTokenCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenCheckInterceptor())
                .addPathPatterns("/v1/todo/**");
    }

    @Bean
    public JwtTokenCheckInterceptor jwtTokenCheckInterceptor() {
        return new JwtTokenCheckInterceptor();
    }
}
