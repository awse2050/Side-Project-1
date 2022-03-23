package com.example.check.api.domains.member.service;

import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.member.repository.MemberRepository;
import com.example.check.web.v1.member.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberCreator {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long create(SignUpRequest request) {
        String encodedPassword = getEncodedPassword(request.getPassword());

        Member entity = new Member(request, encodedPassword);

        Long memberId = memberRepository.save(entity).getId();

        return memberId;
    }

    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
