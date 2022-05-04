package com.example.check.web.v1.comment.controller;

import com.example.check.api.domains.comment.service.CommentCreator;
import com.example.check.api.domains.member.config.JwtProvider;
import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.member.service.MemberDetailsService;
import com.example.check.web.v1.comment.dto.CommentCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
@Log4j2
public class CommentApiController {

    private final CommentCreator commentCreator;
    private final MemberDetailsService memberDetailsService;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CommentCreateDto dto,
                                         @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) {
        String requestEmail = JwtProvider.getSubject(jwtToken.replace("Bearer ", ""));
        log.info("request Email : {}", requestEmail);

        Member findMember = memberDetailsService.findMember(requestEmail);
        log.info("findMember : {}", findMember);

        commentCreator.createComment(dto, findMember);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
