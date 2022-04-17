package com.example.check.web;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtParseExceptionHandler {

    /*
        임시 설정
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> jwtExceptionHandler(RuntimeException e) {
        System.out.println("e : "+e.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
