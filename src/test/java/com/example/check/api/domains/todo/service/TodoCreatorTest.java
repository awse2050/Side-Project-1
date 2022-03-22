package com.example.check.api.domains.todo.service;

import com.example.check.web.v1.todo.dto.TodoCreateDto;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Commit
class TodoCreatorTest {

    @Autowired
    private TodoCreator todoCreator;

    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("Todo Create Service test")
    @Test
    void CREATE_SERVICE_TEST() {
        TodoCreateDto dto = TodoCreateDto.builder()
                .content("내용1")
                .writer("작성자1")
                .checked(false)
                .build();

        Long id = todoCreator.createTodo(dto);
        Optional<Todo> byId = todoRepository.findById(id);

        assertThat(byId).isNotEmpty();
        assertThat(byId.get().getContent()).isEqualTo("내용1");
    }
}