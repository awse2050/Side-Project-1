package com.example.check.web.v1.todo.dto;

import com.example.check.api.domains.todo.entity.Todo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class TodoCreateDto {

    private String content;
    private String writer;
    private boolean checked;

    public Todo bindToEntity() {
        return Todo.builder()
                .content(this.content)
                .writer(this.writer)
                .checked(this.checked)
                .build();
    }
}
