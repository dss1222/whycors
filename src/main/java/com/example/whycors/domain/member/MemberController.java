package com.example.whycors.domain.member;

import com.example.whycors.domain.member.dto.MemberResponseDto;
import com.example.whycors.domain.member.dto.SignupRequestDto;
import com.example.whycors.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/users/signup")
    public String signup(@RequestBody @Valid SignupRequestDto requestDto) {
        memberService.signup(requestDto);
        return "OK";
    }

    @GetMapping("/users/check")
    public MemberResponseDto loginCheck(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new MemberResponseDto(userDetails.getUser());
    }

}
