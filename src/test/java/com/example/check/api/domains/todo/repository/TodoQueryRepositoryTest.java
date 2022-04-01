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

    @BeforeEach
    public void BEFORE_INSERT() {
        for(int j=0; j<20; j++) {
            Todo entity = Todo.builder()
                    .content("todo"+j)
                    .writer("writer"+j)
                    .checked(false)
                    .build();
            for(int i=0; i<=5; i++) {
                entity.addComments(Comment.builder().content("안녕하세요."+i).writer("작성자"+i).todo(entity).build());
            }
            todoRepository.save(entity);
        }
    }

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
                    log.info("todo.getId() : {}", todo.getId());
                    List<Comment> comments = todo.getComments();
                    log.info("comments.size : {}", comments.size());

                    comments.forEach(comment -> {
                        log.info("comment.getId() : {}", comment.getId());
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