package com.example.check.web.v1.todo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoSearchDto {
    // 번호, 내용, 작성자, 체크여부
    private Long todoId;
    private String content;
    private String writer;
    private boolean checked;

    @QueryProjection
    public TodoSearchDto(Long todoId, String content, String writer, boolean checked) {
        this.todoId = todoId;
        this.content = content;
        this.writer = writer;
        this.checked = checked;
    }
}
