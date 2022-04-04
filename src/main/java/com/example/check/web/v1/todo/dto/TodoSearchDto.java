package com.example.check.web.v1.todo.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoSearchDto {
    // 번호, 내용, 작성자, 체크여부
    private Long todoId;
    private String content;
    private String writer;
    private boolean checked;
    // 답글번호, 내용, 작성자, 작성날짜
    private Long commentId;
    private String commentContent;
    private String commentWriter;
    private LocalDateTime regDate;

    @QueryProjection
    public TodoSearchDto(Long todoId, String content, String writer, boolean checked,
                         Long commentId, String commentContent, String commentWriter, LocalDateTime regDate) {
        this.todoId = todoId;
        this.content = content;
        this.writer = writer;
        this.checked = checked;
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentWriter = commentWriter;
        this.regDate = regDate;
    }

}
