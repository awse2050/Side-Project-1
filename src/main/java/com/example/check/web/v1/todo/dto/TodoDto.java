package com.example.check.web.v1.todo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class TodoDto {
    private Long todoId;
    private String content;
    private String writer;
    private boolean checked;
}
