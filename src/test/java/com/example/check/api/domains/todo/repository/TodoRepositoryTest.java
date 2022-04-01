package com.example.check.api.domains.todo.repository;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.comment.repository.CommentRepository;
import com.example.check.api.domains.todo.entity.Todo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Autowired
    private CommentRepository commentRepository;

    final Long id = 1L;

    @BeforeEach
    void BEFORE_CREATE_ENTITY() {
        for(int j=0; j<3; j++) {
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

    @Test
    public void test() {
        List<Todo> all = todoRepository.findAll();
        for (Todo todo : all) {
            log.info(todo.getComments().size());
        }
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

    /*
        1. Cascade 속성을 Remove를 줄 경우 할일을 삭제할때 그 답글들도 같이 삭제된다.
        2. 하지만 각각 한개씩 삭제를 하게되어 Delete SQL이 답글 수 만큼 이루어진다.
        3. 여러개를 삭제하기 위해서 벌크성 삭제를 이룰 경우 직접 Repository를 만들어서 호출한다.
     */
    @DisplayName("todo Delete Test")
    @Test
    @Order(4)
    void DELETE_TEST() {
        commentRepository.deleteByTodoId(id); // JPQL
        todoRepository.deleteById(id);

        assertThat(todoRepository.findById(id)).isEmpty();
    }
}