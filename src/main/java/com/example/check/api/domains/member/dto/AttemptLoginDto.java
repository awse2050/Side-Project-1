package com.example.check.api.domains.member.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttemptLoginDto {

    @NotNull(message = "email cannot be null")
    @Size(min = 2, message = "email not be less then two characters")
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 8, message = "password not be less then 8 characters")
    private String password;

    public UsernamePasswordAuthenticationToken bindToAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.email, this.password);
    }
}
