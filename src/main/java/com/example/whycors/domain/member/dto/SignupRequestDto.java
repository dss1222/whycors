package com.example.whycors.domain.member.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignupRequestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    public String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    public String password;

    @NotBlank(message = "비밀번호를 한번 더 입력해주세요.")
    public String passwordCheck;

    @NotBlank(message = "닉네임을 입력해주세요.")
    public String nickname;
}
