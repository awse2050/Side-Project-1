package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.todo.entity.Todo;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Log4j2
@Transactional
@Commit
class TodoQueryRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoQueryRepository todoQueryRepository;

    @DisplayName("findAll By Paging Query Test")
    @Test
    public void QUERYDSL_FIND_ALL_BY_PAGING_TEST() {
        PageRequest of = PageRequest.of(0, 10);

        List<String> collect = todoQueryRepository.todosFindAllByPaging(of).stream().map(
                todo -> todo.getComments().get(0).getContent()
        ).collect(Collectors.toList());

        Assertions.assertThat(collect).isNotNull();
    }

    @DisplayName("findAll Query Repository Test")
    @Test
    public void QUERYDSL_FIND_ALL_TEST() {
        todoQueryRepository.todosFindAll()
                .forEach(todo -> {
                    List<Comment> comments = todo.getComments();

                    comments.forEach(comment -> {
                        comment.getId();
                    });
                });
    }

    @DisplayName("search Query Repository Test")
    @Test
    public void QUERYDSL_SEARCH_TEST() {

        todoQueryRepository.searchTodo("3").stream()
                .forEach(result -> log.info(" result .... : {}", result));
    }

}