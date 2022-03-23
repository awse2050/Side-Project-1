package com.example.check.api.domains.member.repository;

import com.example.check.api.domains.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"memberRoles"}, type = EntityGraph.EntityGraphType.LOAD)
    Optional<Member> findByEmail(String email);
}
