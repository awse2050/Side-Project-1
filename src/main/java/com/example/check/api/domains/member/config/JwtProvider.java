package com.example.check.api.domains.member.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.Optional;

@Log4j2
public final class JwtProvider {

    private static final long EXPIRE_TIME = 86_400_000;
    private static final String SECRET_KEY = "login_token";
    private static final String AUTHENTICATION_HEADER_PREFIX = "Bearer ";

    public static String createToken(String email) {
        return Jwts.builder()
                .setSubject(email) // UUID를 붙여서 써본다
                // 하루짜리 토큰을 만든다.
                .setExpiration(
                        new Date(System.currentTimeMillis() +
                                EXPIRE_TIME))
                // 암호화를 위한 알고리즘을 추가, 조합키로 시크릿값을 추가
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static boolean isJwtValid(String jwtToken) {
        String subject = getSubject(jwtToken);

        return subject != null && !subject.isEmpty();
    }

    public static String getSubject(String jwtToken) {
        String subject = getClaims(jwtToken).getSubject();
        log.info("subject : {}", subject);
        return subject;
    }

    private static Jws<Claims> getParsedClaimsJws(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwtToken);
    }

    private static Claims getClaims(String jwtToken) {
        Jws<Claims> claimsJws = getParsedClaimsJws(jwtToken);
        log.info("claimsJws : {}", claimsJws);
        return claimsJws.getBody();
    }

    public static String getEncodedEmail(String jwtToken) {
        return jwtToken.replace(AUTHENTICATION_HEADER_PREFIX, "");
    }
}
