package com.example.check.api.domains.todo.service;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.attach.repository.AttachRepository;
import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.comment.repository.CommentRepository;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoQueryRepository;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.todo.dto.TodoSelectDto;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class TodoDeleterTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AttachRepository attachRepository;

    @Autowired
    private TodoQueryRepository todoQueryRepository;

    @Autowired
    private TodoDeleter todoDeleter;

    @BeforeEach
    public void BEFORE_EACH_CREATE() {
        log.info("before create..");
//        for(int j=0; j<10; j++) {
//            Todo entity = Todo.builder()
//                    .content("todo"+j)
//                    .writer("writer"+j)
//                    .checked(false)
//                    .build();
//            for(int i=0; i<=5; i++) {
//                entity.addComments(Comment.builder().content("안녕하세요."+i).writer("작성자"+i).todo(entity).build());
//            }
//
//            if(j%2==0) {
//                log.info("attach ....");
//                Attach attach = new Attach("uploadFileName"+j , "fileName"+j);
//                attach.setTodo(entity);
//
//                entity.addAttach(attach);
//            }
//
//            todoRepository.save(entity);
//        }
        log.info("complete..");
    }

    @DisplayName("Todo Delete Test")
    @Test
    public void TODO_DELETE_TEST() {
        todoDeleter.removeTodo(1L);

        List<TodoSelectDto> todos = todoQueryRepository.findTodos();

        Assertions.assertThat(todos.get(0).getTodoDto().getTodoId()).isNotEqualTo(1L);
    }
}