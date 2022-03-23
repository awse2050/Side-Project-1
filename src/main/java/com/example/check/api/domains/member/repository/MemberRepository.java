package com.example.check.api.domains.member.repository;

import com.example.check.api.domains.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    @Query("select m from Member m join fetch m.memberRoles where m.email=:email")
    Optional<Member> findByEmail(@Param("email") String email);
}
