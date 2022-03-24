package com.example.check.web.v1.member.controller;

import com.example.check.web.WebTestSetUp;
import com.example.check.web.v1.member.dto.SignUpRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@Log4j2
class MemberApiControllerTest extends WebTestSetUp {

    @DisplayName("Member Create API Success Test")
    @Test
    void MEMBER_CREATE_API_SUCCESS_TEST() throws Exception {

        mockMvc.perform(post("/v1/member")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJsonFormMessageBody(validSignUpRequest())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("Member Create API Fail Test")
    @Test
    void MEMBER_CREATE_API_FAIL_TEST() throws Exception {

        mockMvc.perform(post("/v1/member")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJsonFormMessageBody(inValidSignUpRequest())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private SignUpRequest validSignUpRequest() {
        return SignUpRequest.builder()
                .email("slsl@naver.com")
                .password("1212")
                .name("asksk")
                .build();
    }

    private SignUpRequest inValidSignUpRequest() {
        return SignUpRequest.builder()
                .email("navercom")
                .password("12121212")
                .name("asksk")
                .build();
    }

}