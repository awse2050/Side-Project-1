package com.example.check.api.domains.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class ResponseLoginDto extends User {

    // TODO : Validation 추가
    private String email;
    private String name;

    public ResponseLoginDto(String email, String name, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.email = email;
        this.name = name;
    }
}
