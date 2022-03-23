package com.example.check.api.domains.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    MEMBER("ROLE_MEMBER");

    private String name;

}
