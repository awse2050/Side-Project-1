package com.example.check.web.v1.attach.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Log4j2
public class AttachController {

    @Value("${file.path}")
    private String filePath;

    @GetMapping("/upload-form")
    public String uploadForm() {
        log.info("upload ....");
        log.info("filePath ... : {}", filePath);
        return "upload-form";
    }

    @PostMapping("/upload")
    public void upload(@RequestParam List<MultipartFile> attachFile) throws IOException {

        for (MultipartFile file : attachFile) {
            log.info("file ... : {}", file);
            log.info("file.getOriginalFilename() : {}", file.getOriginalFilename());

            File file1 = new File(filePath + file.getOriginalFilename());
            log.info(file1.getAbsolutePath());

            file.transferTo(new File(filePath+file.getOriginalFilename()));
        }
    }
}
