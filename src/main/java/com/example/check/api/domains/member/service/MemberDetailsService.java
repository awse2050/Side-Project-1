package com.example.check.api.domains.member.service;

import com.example.check.api.domains.member.dto.ResponseLoginDto;
import com.example.check.api.domains.member.entity.Member;
import com.example.check.api.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private static final String USER_NOT_FOUND_MESSAGE = "없는 사용자 입니다.";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email : {}", email);

        Member findMember = findMember(email);

        log.info("findMember : {} ", findMember);

        ResponseLoginDto responseLoginDto = new ResponseLoginDto(findMember.getEmail(),
                findMember.getName(),
                findMember.getPassword(),
                findMember.getMemberRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                        .collect(Collectors.toSet()));

        log.info("loginCheckDto : {}", responseLoginDto);

        return responseLoginDto;
    }

    public Member findMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
    }
}
