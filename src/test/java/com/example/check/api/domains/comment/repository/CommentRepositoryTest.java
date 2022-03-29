package com.example.check.api.domains.comment.repository;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Transactional
@Commit
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TodoRepository todoRepository;

    private final Long id = 1L;

    @BeforeEach
    public void INSERT_TODO() {
        Todo todoEntity = Todo.builder()
                .content("오늘의 할일입니다.")
                .checked(false)
                .writer("작성자2")
                .build();

        todoRepository.save(todoEntity);
    }

    @DisplayName("Comment Insert Test")
    @Test
    @Order(1)
    public void COMMENT_INSERT_TEST() {

        Comment entity = getExampleEntity();

        Long commentId = commentRepository.save(entity).getId();

        assertThat(commentId).isEqualTo(id);

        entity = commentRepository.findById(id).get();

        assertThat(entity.getTodo().getContent()).isEqualTo("오늘의 할일입니다.");
    }

    @DisplayName("findById Test")
    @Test
    @Order(2)
    public void FIND_BY_ID_TEST() {
        Comment entity = getExampleEntity();
        Long entityId = commentRepository.save(entity).getId();

        Comment findComment = commentRepository.findById(entityId).orElse(null);

        assertThat(findComment).isNotNull();
        assertThat(findComment).isInstanceOf(Comment.class);
        assertThat(findComment.getContent()).isEqualTo("확인했습니다.");
    }

    @DisplayName("Update Test")
    @Test
    @Order(3)
    public void UPDATE_TEST() {
        Comment entity = getExampleEntity();
        Long entityId = commentRepository.save(entity).getId();

        Comment findComment = commentRepository.findById(entityId).orElse(null);
        findComment.changeContent("확인못했습니다.");

        commentRepository.save(findComment);

        findComment = commentRepository.findById(entityId).orElse(null);

        assertThat(findComment.getContent()).isEqualTo("확인못했습니다.");
    }

    @DisplayName("delete Test")
    @Test
    @Order(4)
    public void DELETE_TEST() {
        Comment entity = getExampleEntity();
        Long entityId = commentRepository.save(entity).getId();

        commentRepository.deleteById(entityId);

        entity = commentRepository.findById(entityId)
                .orElse(null);

        assertThat(entity).isNull();
    }

    private Comment getExampleEntity() {
        return  Comment.builder()
                .content("확인했습니다.")
                .writer("사용자2")
                .todo(todoRepository.findById(id).get())
                .build();
    }
}