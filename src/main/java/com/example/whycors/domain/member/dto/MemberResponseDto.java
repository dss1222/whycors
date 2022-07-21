package com.example.whycors.domain.member.dto;

import com.example.whycors.domain.member.Member;
import lombok.Data;

@Data
public class MemberResponseDto {
    public String username;
    public String nickname;

    public MemberResponseDto(Member member) {
        this.username = member.getUsername();
        this.nickname = member.getNickname();
    }
}
