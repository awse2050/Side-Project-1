package com.example.check.api.domains.member.service;

import com.example.check.web.v1.member.dto.SignUpRequest;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
@Commit
class MemberCreatorTest {

    @Autowired
    private MemberCreator memberCreator;

    @DisplayName("Member create Test")
    @Test
    public void CREATE_TEST() {

        SignUpRequest user11 = SignUpRequest.builder()
                .email("asd@naver.com")
                .name("user11")
                .password("12121212")
                .build();

        Long id = memberCreator.create(user11);

        Assertions.assertThat(id).isNotNull();
    }

}