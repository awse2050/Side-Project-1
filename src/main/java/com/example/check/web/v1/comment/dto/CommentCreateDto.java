package com.example.check.web.v1.comment.dto;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.todo.entity.Todo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CommentCreateDto {

    @Positive(message = "todo Id null")
    private Long todoId;
    @NotNull(message = "답글을 정확히 입력하세요.")
    private String content;
    @NotEmpty(message = "로그인이 필요합니다.")
    private String writer;

    public Comment bindToEntity(Todo todo) {
        return Comment.builder()
                .content(this.content)
                .writer(this.writer)
                .todo(todo)
                .build();
    }
}
