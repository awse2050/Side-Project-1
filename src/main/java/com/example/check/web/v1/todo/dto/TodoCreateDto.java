package com.example.check.web.v1.todo.dto;

import com.example.check.api.domains.member.entity.Member;
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
    private boolean checked;

    public Todo bindToEntity(Member member) {
        return Todo.builder()
                .content(this.content)
                .member(member)
                .checked(this.checked)
                .attach(null)
                .build();
    }
}
