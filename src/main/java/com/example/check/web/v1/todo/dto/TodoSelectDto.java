package com.example.check.web.v1.todo.dto;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.comment.dto.CommentResponses;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class TodoSelectDto {

    // 번호, 내용, 작성자, 체크여부
    private Long todoId;
    private String content;
    private String writer;
    private boolean checked;

    private List<CommentResponses> comments;

    /*
        첨부파일 추가 필요
     */
    public TodoSelectDto(Todo todo, List<CommentResponses> comments) {
        this.todoId = todo.getId();
        this.content = todo.getContent();
        this.writer = todo.getWriter();
        this.checked = todo.isChecked();
        this.comments = comments;
    }
}
