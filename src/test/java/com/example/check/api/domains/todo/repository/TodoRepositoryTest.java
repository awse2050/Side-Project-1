package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Transactional
@Commit
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    final Long id = 1L;

    @BeforeEach
    void BEFORE_CREATE_ENTITY() {
        Todo entity = Todo.builder()
                .content("todo")
                .writer("writer")
                .checked(false)
                .build();

        todoRepository.save(entity);
    }

    @DisplayName("todo Create Test")
    @Test
    @Order(1)
    void TODO_CREATE_TEST() {
        Todo entity = Todo.builder()
                .content("todo1")
                .writer("writer1")
                .checked(false)
                .build();

        todoRepository.save(entity);

        assertThat(todoRepository.count()).isGreaterThan(1);
    }

    @DisplayName("todo FindById Test")
    @Test
    @Order(2)
    void FIND_BY_ID_TEST() {

        Optional<Todo> byId = todoRepository.findById(id);

        if(byId.isPresent()) {
            log.info("entity : {}", byId.get());
        }

        assertThat(byId.isPresent()).isTrue();
    }

    @DisplayName("todo update(dirtyCheck) test")
    @Test
    @Order(3)
    void UPDATE_TEST() {
        Todo entity = todoRepository.findById(id)
                .orElse(Todo.builder()
                    .content("default1")
                    .writer("de_writer1")
                    .checked(false)
                    .build());

        entity.changeChecked();
        entity.changeContent("change Content1");

        log.info("change entity : {}", entity);

        todoRepository.save(entity);

        assertThat(todoRepository.findById(id).get().getContent()).isEqualTo("change Content1");
    }

    @DisplayName("todo Delete Test")
    @Test
    @Order(4)
    void DELETE_TEST() {
        todoRepository.deleteById(id);

        assertThat(todoRepository.findById(id)).isEmpty();
    }
}