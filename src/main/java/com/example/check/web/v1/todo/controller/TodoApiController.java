package com.example.check.web.v1.todo.controller;

import com.example.check.api.domains.todo.service.TodoCreator;
import com.example.check.api.domains.todo.service.TodoDeleter;
import com.example.check.api.domains.todo.service.TodoSelector;
import com.example.check.web.v1.todo.dto.TodoCreateDto;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoApiController {

    private final TodoCreator todoCreator;
    private final TodoSelector todoSelector;
    private final TodoDeleter todoDeleter;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody @Validated TodoCreateDto createDto) {

        Long id = todoCreator.createTodo(createDto);
        log.info("id : {}", id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<TodoSelectDto>> selectAll() {
        List<TodoSelectDto> dtoList = todoSelector.findAll();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/search/{content}")
    public ResponseEntity<List<TodoSelectDto>> search(@PathVariable("content") String searchContent) {
        List<TodoSelectDto> dtoList = todoSelector.searchTodo(searchContent);
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> remove(@PathVariable Long todoId) {
        todoDeleter.removeTodo(todoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
