package com.example.check.api.domains.todo.service;

import com.example.check.web.v1.todo.dto.TodoCreateDto;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TodoCreator {

    private final TodoRepository todoRepository;

    @Transactional
    public Long createTodo(TodoCreateDto createDto) {
        Todo entity = createDto.bindToEntity();

        return todoRepository.save(entity).getId();
    }

}
