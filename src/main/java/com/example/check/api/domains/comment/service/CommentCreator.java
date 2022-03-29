package com.example.check.api.domains.comment.service;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.comment.repository.CommentRepository;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.exception.TodoNotFoundException;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.comment.dto.CommentCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCreator {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public Long createComment(CommentCreateDto dto) {

        Todo todo = todoRepository.findById(dto.getTodoId())
                .orElseThrow(() -> new TodoNotFoundException("존재하지 않는 할일 입니다."));

        Comment comment = dto.bindToEntity(todo);

        Long commentId = commentRepository.save(comment).getId();

        return commentId;
    }
}
