package com.example.check.web.v1.todo.dto;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.web.v1.attach.dto.AttachDto;
import com.example.check.web.v1.comment.dto.CommentResponses;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@ToString
public class TodoSelectDto {
    // 번호, 내용, 작성자, 체크여부
    private TodoDto todoDto;
    private List<CommentResponses> comments;
    private AttachDto attachDto;

    public TodoSelectDto(Todo todo,
                         List<Comment> comments,
                         Attach attach) {
        this.todoDto = todo.bindToTodoDto();
        this.comments = formatCommentEntity(comments);
        this.attachDto = isEmptyAttach(attach);
    }

    private List<CommentResponses> formatCommentEntity(List<Comment> comments) {
        return comments.stream().map(Comment::bindToDto).collect(Collectors.toList());
    }
    private AttachDto isEmptyAttach(Attach attach) {
        return attach != null ? attach.bindToAttachDto() : null;
    }
}
