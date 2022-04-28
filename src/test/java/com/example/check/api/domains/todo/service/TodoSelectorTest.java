package com.example.check.api.domains.todo.service;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.comment.service.CommentCreator;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Log4j2
class TodoSelectorTest {

    @Autowired
    private TodoSelector todoSelector;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void BEFORE_EACH_CREATE() {
        log.info("before create..");
            for(int j=0; j<2; j++) {
                Todo entity = Todo.builder()
                        .content("todo"+j)
                        .writer("writer"+j)
                        .checked(false)
                        .build();
                for(int i=0; i<=5; i++) {
                    entity.addComments(Comment.builder().content("안녕하세요."+i).writer("작성자"+i).todo(entity).build());
                }
                log.info("todo.... : {}", entity);
                entity.getComments().forEach(i -> log.info("comment : {}", i));
                todoRepository.save(entity);
            log.info("count : {}", todoRepository.count());
            }
        log.info("complete..");
    }

    @DisplayName("Todo Selector Find All Test")
    @Test
    public void TODO_SELECTOR_FIND_ALL_TEST() {

        List<TodoSelectDto> all = todoSelector.findAll();
        log.info("all.size : {}", all.size());
        for (TodoSelectDto todo : all) {
            log.info(todo);
        }
    }
}