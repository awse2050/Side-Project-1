package com.example.check.web.v1.attach.controller.advice;

import com.example.check.api.domains.attach.exception.FailAttachFileUploadException;
import com.example.check.web.v1.attach.controller.AttachApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AttachApiController.class)
public class AttachApiControllerAdvice {

    @ExceptionHandler(value = {FailAttachFileUploadException.class})
    public ResponseEntity<String> failUploadExceptionHandler(FailAttachFileUploadException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
