package com.example.check.api.domains.member.repository;

import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.member.entity.MemberRole;
import com.example.check.api.domains.member.entity.Role;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Log4j2
@Transactional
@Commit
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void INSERT_MEMBER() {
        Member member = Member.builder()
                .email("ddd@naver.com")
                .name("name..")
                .password("1234")
                .build();

        member.addRoles(MemberRole.builder().role(Role.MEMBER).member(member).build());

        memberRepository.save(member);

        log.info("member : {}", member);
    }

    @DisplayName("Member findByEmail Test")
    @Test
    void FIND_BY_ID_TEST() {
        Optional<Member> byEmail = memberRepository.findByEmail("ddd@naver.com");

        if(byEmail.isPresent()) {
            log.info("member : {}", byEmail.get());
            for (MemberRole memberRole : byEmail.get().getMemberRoles()) {
                log.info("role .. : {} ", memberRole);
            }
        }

        assertThat(byEmail.isPresent()).isTrue();
    }
}