package com.example.check.api.domains.attach.exception;

public class FailAttachFileUploadException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "업로드에 실패했습니다.";

    public FailAttachFileUploadException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}
