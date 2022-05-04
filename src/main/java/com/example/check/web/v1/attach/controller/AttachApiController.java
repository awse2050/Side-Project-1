package com.example.check.web.v1.attach.controller;

import com.example.check.api.domains.attach.service.AttachCreator;
import com.example.check.api.domains.member.config.JwtProvider;
import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.member.service.MemberDetailsService;
import com.example.check.web.v1.attach.dto.CreatedAttachFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
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

    private final MemberDetailsService memberDetailsService;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CreatedAttachFileDto> upload(@RequestParam MultipartFile attachFile,
                                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken) throws IOException{

        String requestEmail = JwtProvider.getSubject(jwtToken.replace("Bearer ", ""));
        log.info("request Email : {}", requestEmail);

        Member findMember = memberDetailsService.findMember(requestEmail);
        log.info("findMember : {}", findMember);

        CreatedAttachFileDto attachFileDto = attachCreator.create(attachFile, findMember);

        return new ResponseEntity<>(attachFileDto, HttpStatus.CREATED);
    }
}
