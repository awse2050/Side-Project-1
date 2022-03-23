package com.example.check.web.v1.member.controller;

import com.example.check.web.v1.member.dto.SignUpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
class MemberApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("Member Create API Success Test")
    @Test
    void MEMBER_CREATE_API_SUCCESS_TEST() throws Exception {

        mockMvc.perform(post("/v1/member")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(validSignUpRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    // TODO : 실패하는 API Test 만들기.

    private SignUpRequest validSignUpRequest() {
        return SignUpRequest.builder()
                .email("slsl@naver.com")
                .password("1212")
                .name("asksk")
                .build();
    }
}