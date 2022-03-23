package com.example.check.api.domains.member.entity;

import com.example.check.api.util.entity.DateEntity;
import com.example.check.web.v1.member.dto.SignUpRequest;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString(exclude = {"memberRoles"})
public class Member extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    // TODO : 각 필드 nullable 추가하기.
    // TODO :email 유니크 키 추가
    private String email;

    private String password;

    private String name;

    /*
        1. 기본값이지만 fetch를 명시
        2. 값타입 컬렉션 대신이므로 엔티티 구조를 이용.
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<MemberRole> memberRoles = new HashSet<>();

    public Member(SignUpRequest signUpRequest, String encodedPassword) {
        this.email = signUpRequest.getEmail();
        this.name = signUpRequest.getName();

        setPassword(encodedPassword);

        addRoles(MemberRole.builder()
                .role(Role.MEMBER)
                .member(this)
                .build());
    }

    public void addRoles(MemberRole memberRole) {
        System.out.println("memberRole.. : " + memberRole);
        memberRoles.add(memberRole);
    }

    private void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
