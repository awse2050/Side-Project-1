package com.example.check.api.domains.attach.service;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.attach.entity.AttachFileStore;
import com.example.check.api.domains.attach.exception.FailAttachFileUploadException;
import com.example.check.api.domains.attach.repository.AttachRepository;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.attach.dto.ResponseAttachFileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Log4j2
public class AttachCreator {

    private final TodoRepository todoRepository;
    private final AttachFileStore attachFileStore;

    @Transactional(rollbackFor = IOException.class, readOnly = false)
    public ResponseAttachFileDto create(MultipartFile multipartFile) throws IOException {

        Attach attach = attachFileStore.storeAttachFile(multipartFile)
                .orElseThrow(() -> new FailAttachFileUploadException());
        log.info("attach : {}", attach);

        Todo todo = new Todo(attach);

        todoRepository.save(todo);

        return new ResponseAttachFileDto(attach, todo);
    }
}
