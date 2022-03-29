package com.example.check.web.v1.comment.controller.advice;

import com.example.check.web.v1.comment.controller.CommentApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CommentApiController.class)
public class CommentApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> bindingException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
