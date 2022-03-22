package com.example.check.web.v1.todo.dto;

import com.example.check.api.domains.todo.entity.Todo;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class TodoCreateDto {

    @NotNull(message = "내용을 작성해주세요.")
    private String content;
    @NotNull
    private String writer;
    @NotNull
    private boolean checked;

    public Todo bindToEntity() {
        return Todo.builder()
                .content(this.content)
                .writer(this.writer)
                .checked(this.checked)
                .build();
    }
}
