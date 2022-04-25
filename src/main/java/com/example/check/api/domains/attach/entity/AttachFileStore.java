package com.example.check.api.domains.attach.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
public class AttachFileStore {

    @Value("${file.path}")
    private String fileDir;

    private static final String FILENAME_DELIMITER = "-";

    public Optional<Attach> storeAttachFile(MultipartFile multipartFile) throws IOException {
        return Optional.ofNullable(saveFile(multipartFile));
    }

    private Attach saveFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String changedFileName = toStoredOriginalFileName(originalFileName);

        multipartFile.transferTo(new File(fileDir+changedFileName));

        return new Attach(originalFileName, changedFileName);
    }

    private String toStoredOriginalFileName(String originalFileName) {
        String uniqueString = UUID.randomUUID().toString();

        return uniqueString + FILENAME_DELIMITER + originalFileName;
    }
}
