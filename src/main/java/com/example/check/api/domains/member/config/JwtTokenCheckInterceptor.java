package com.example.check.api.domains.member.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Log4j2
public class JwtTokenCheckInterceptor implements HandlerInterceptor {

    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";
    /*
        헤더의 유효성을 검사하는 로직
        URI를 인터셉터로 필터링.
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle....");

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("header : {}", header);
        if(Objects.isNull(header) || !header.contains(AUTHENTICATION_HEADER_PREFIX) || header.isEmpty()){
            // TODO : 예외발생
            log.info("Objects.isNull(header) || !header.contains(AUTHENTICATION_HEADER_PERFIX) || header.isEmpty() ..");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

        String token = header.replace("Bearer ", "");
        log.info("token ... : {}", token);
        boolean jwtValid = JwtProvider.isJwtValid(token);
        log.info("jwtValid : {}", jwtValid);

        if(!jwtValid) {
            // TODO : 예외발생
            log.info("Not Valid JWT Token");
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return false;
        }

        return true;
    }
}
