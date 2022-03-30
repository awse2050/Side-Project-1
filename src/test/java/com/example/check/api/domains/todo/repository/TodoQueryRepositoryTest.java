package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.todo.entity.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
@Commit
class TodoQueryRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoQueryRepository todoQueryRepository;

    @BeforeEach
    public void BEFORE_INSERT() {
        todoRepository.save(Todo.builder().writer("작성자1").content("안녕하세요1").checked(false).build());
        todoRepository.save(Todo.builder().writer("작성자2").content("안녕하세요3").checked(false).build());
        todoRepository.save(Todo.builder().writer("작성자3").content("안녕하세요1").checked(false).build());
        todoRepository.save(Todo.builder().writer("작성자4").content("안녕하세요3").checked(false).build());
        todoRepository.save(Todo.builder().writer("작성자5").content("안녕하세요1").checked(false).build());
        todoRepository.save(Todo.builder().writer("작성자6").content("안녕하세요3").checked(false).build());
    }

    @DisplayName("search Query Repository Test")
    @Test
    public void QUERYDSL_SEARCH_TEST() {

        todoQueryRepository.searchTodo("3").stream()
                .forEach(result -> log.info(" result .... : {}", result));
    }

}