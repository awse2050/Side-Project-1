package com.example.check.web.v1.member.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class SignUpRequest {

    @Email(message = "올바른 이메일 형식을 입력하세요.")
    private String email;
    @NotBlank
    @Size(min = 4, max = 12, message = "4~12")
    private String password;
    @NotBlank(message = "이름을 정확히 입력하세요.")
    private String name;

}
