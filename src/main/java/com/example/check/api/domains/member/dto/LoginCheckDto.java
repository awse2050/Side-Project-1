package com.example.check.api.domains.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class LoginCheckDto extends User {

    // TODO : Validation 추가
    private String email;
    private String name;

    public LoginCheckDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
    }

    public void setName(String name) {
        this.name = name;
    }
}
