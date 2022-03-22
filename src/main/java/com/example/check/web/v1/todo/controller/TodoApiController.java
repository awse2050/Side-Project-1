package com.example.check.web.v1.todo.controller;

import com.example.check.api.domains.todo.service.TodoCreator;
import com.example.check.web.v1.todo.dto.TodoCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoApiController {

    private final TodoCreator todoCreator;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated TodoCreateDto createDto) {

        Long id = todoCreator.createTodo(createDto);
        log.info("id : {}", id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
