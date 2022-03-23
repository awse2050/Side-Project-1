package com.example.check.web.v1.member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class SignUpRequest {

    // TODO: Validation 추가하기.
    private String email;
    private String password;
    private String name;

}
