package com.example.check.web.v1.comment.controller;

import com.example.check.api.domains.todo.entity.Todo;
import com.example.check.api.domains.todo.repository.TodoRepository;
import com.example.check.web.WebTestSetUp;
import com.example.check.web.v1.comment.dto.CommentCreateDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Log4j2
class CommentApiControllerTest extends WebTestSetUp {

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    public void INSERT_BEFORE() {
        todoRepository.save(Todo.builder().writer("작성자1").content("오늘할일 입니다.").checked(false).build());
    }

    @DisplayName("Comment Create API Success Test")
    @Test
    void COMMENT_CREATE_API_SUCCESS_TEST() throws Exception {

        mockMvc.perform(post("/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonFormMessageBody(validCreateDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @DisplayName("Comment Create API Fail Test")
    @Test
    void COMMENT_CREATE_API_FAIL_TEST() throws Exception {

        mockMvc.perform(post("/v1/comment")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonFormMessageBody(invalidCreateDto())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private CommentCreateDto validCreateDto() {
        return CommentCreateDto.builder()
                .todoId(1L)
                .content("확인이요")
                .writer("작성자3")
                .build();
    }

    private CommentCreateDto invalidCreateDto() {
        return CommentCreateDto.builder()
                .todoId(0L)
                .content("확인이요")
                .writer("작성자3")
                .build();
    }
}