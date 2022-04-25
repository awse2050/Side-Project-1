package com.example.check.web.v1.attach.controller;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.attach.service.AttachCreator;
import com.example.check.web.v1.attach.dto.ResponseAttachFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/attach")
@Log4j2
public class AttachApiController {

    private final AttachCreator attachCreator;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseAttachFileDto> upload(@RequestParam MultipartFile attachFile) throws IOException {
        ResponseAttachFileDto attachFileDto = attachCreator.create(attachFile);

        return new ResponseEntity<>(attachFileDto, HttpStatus.CREATED);
    }
}
