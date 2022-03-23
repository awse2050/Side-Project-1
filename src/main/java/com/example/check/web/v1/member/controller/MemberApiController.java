package com.example.check.web.v1.member.controller;

import com.example.check.api.domains.member.service.MemberCreator;
import com.example.check.web.v1.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/member")
@Log4j2
public class MemberApiController {

    private final MemberCreator memberCreator;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {

        log.info("signUpRequest : {} ", signUpRequest);

        memberCreator.create(signUpRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
