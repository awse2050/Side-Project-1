package com.example.check.api.domains.comment.service;

import com.example.check.api.domains.comment.entity.Comment;
import com.example.check.api.domains.comment.repository.CommentRepository;
import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.v1.comment.dto.CommentCreateDto;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
@Commit
class CommentCreatorTest {

    @Autowired
    private CommentCreator commentCreator;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void TODO_INSERT() {
//        todoRepository.save(Todo.builder().content("안녕하세요").checked(false).writer("작성자1").build());
    }

    @DisplayName("Comment Create Test")
    @Test
    public void COMMENT_CREATE_TEST() {
//
//        CommentCreateDto dto = CommentCreateDto.builder()
//                .todoId(1L)
//                .content("확인했습니다.")
//                .writer("테스터1")
//                .build();
//
//        Long commentId =  commentCreator.createComment(dto);
//
//        Comment comment = commentRepository.findById(commentId).orElse(null);
//
//        assertThat(comment).isNotNull();
//        assertThat(comment.getTodo().getContent()).isEqualTo("안녕하세요");
    }

}