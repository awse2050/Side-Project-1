package com.example.check.web.v1.comment.controller;

import com.example.check.api.domains.comment.service.CommentCreator;
import com.example.check.web.v1.comment.dto.CommentCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/comment")
@Log4j2
public class CommentApiController {

    private final CommentCreator commentCreator;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated CommentCreateDto dto) {
        commentCreator.createComment(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
