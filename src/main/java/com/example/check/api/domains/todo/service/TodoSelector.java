package com.example.check.api.domains.todo.service;

import com.example.check.api.domains.todo.repository.TodoQueryRepository;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional(readOnly = true)
public class TodoSelector {

    private final TodoQueryRepository todoQueryRepository;

    /*
        유효성 검사 추가필요
     */
    public List<TodoSelectDto> findAll() {
        List<TodoSelectDto> todos = todoQueryRepository.todosFindAll();

        return todos;
    }
}
