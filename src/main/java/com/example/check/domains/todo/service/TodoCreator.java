package com.example.check.domains.todo.service;

import com.example.check.domains.todo.dto.TodoCreateDto;
import com.example.check.domains.todo.entity.Todo;
import com.example.check.domains.todo.repository.TodoRepository;
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
