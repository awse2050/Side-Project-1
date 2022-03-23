package com.example.check.api.domains.member.entity;

import com.example.check.api.util.entity.DateEntity;
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

    private String email;

    private String password;

    private String name;

    /*
        1. 기본값이지만 fetch를 명시
        2. 값타입 컬렉션 대신이므로 엔티티 구조를 이용.
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> memberRoles = new HashSet<>();

    public void addRoles(MemberRole memberRole) {
        memberRoles.add(memberRole);
    }
}
