package com.example.check.web.v1.attach.dto;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.todo.entity.Todo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ResponseAttachFileDto {

    private Long attachId;
    private Long todoId;
    private String uploadFileName;
    private String toSavedFileName;

    public ResponseAttachFileDto(Attach attach, Todo todo) {
        this.attachId = attach.getId();
        this.todoId = todo.getId();
        this.uploadFileName = attach.getUploadFileName();
        this.toSavedFileName = attach.getToSavedFileName();
    }
}
