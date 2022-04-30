package com.example.check.api.domains.todo.service;


import com.example.check.api.domains.attach.repository.AttachRepository;
import com.example.check.api.domains.comment.repository.CommentRepository;
import com.example.check.api.domains.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoDeleter {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final AttachRepository attachRepository;

    @Transactional
    public void removeTodo(Long todoId) {
        attachRepository.deleteByTodoId(todoId);
        commentRepository.deleteByTodoId(todoId);
        todoRepository.deleteByTodoId(todoId);
    }
}
