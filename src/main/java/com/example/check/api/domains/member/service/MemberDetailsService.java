package com.example.check.api.domains.member.service;

import com.example.check.api.domains.member.dto.LoginCheckDto;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("email : {}", email);

        Member findMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("없는 사용자 입니다."));

        log.info("findMember : {} ", findMember);

        LoginCheckDto loginCheckDto = new LoginCheckDto(findMember.getEmail(),
                findMember.getPassword(),
                findMember.getMemberRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                        .collect(Collectors.toSet()));

        loginCheckDto.setName(findMember.getName());
        log.info("loginCheckDto : {}", loginCheckDto);

        return loginCheckDto;
    }
}
