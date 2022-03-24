package com.example.check.web.v1.todo.controller;

import com.example.check.web.WebTestSetUp;
import com.example.check.web.v1.todo.dto.TodoCreateDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Log4j2
class TodoApiControllerTest extends WebTestSetUp {

    @DisplayName("Todo Create API Success Test")
    @Test
    void TODO_CREATE_API_SUCCESS_TEST() throws Exception {

        mockMvc.perform(post("/v1/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJsonFormMessageBody(validCreateDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("Todo Create API Fail Test")
    @Test
    void TODO_CREATE_API_FAIL_TEST() throws Exception {

        mockMvc.perform(post("/v1/todo")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJsonFormMessageBody(inValidCreateDto())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private TodoCreateDto validCreateDto() {
        return TodoCreateDto.builder()
                .content("오늘 할일1")
                .writer("작성자1")
                .checked(false)
                .build();
    }

    private TodoCreateDto inValidCreateDto() {
        return TodoCreateDto.builder()
                .checked(false)
                .build();
    }
}