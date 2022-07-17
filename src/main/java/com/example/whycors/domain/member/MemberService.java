package com.example.whycors.domain.member;

import com.example.whycors.global.exceptionhandler.ErrorMessage;
import com.example.whycors.global.exceptionhandler.ex.UserException;
import com.example.whycors.domain.member.dto.SignupRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Transactional
    public void signup(SignupRequestDto requestDto) {
        Optional<Member> foundUsername = memberRepository.findByUsername(requestDto.getUsername());
        if (foundUsername.isPresent()) {
            throw new UserException(ErrorMessage.duplicatedMemberId);
        }
        Optional<Member> foundNickanme = memberRepository.findByNickname(requestDto.getNickname());
        if (foundNickanme.isPresent()) {
            throw new UserException(ErrorMessage.duplicatedNickname);
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordCheck())) {
            throw new UserException(ErrorMessage.duplicatedPassword);
        }
        String password = passwordEncoder.encode(requestDto.getPassword());

        Member member = new Member(requestDto.getUsername(), password, requestDto.getNickname());
        memberRepository.save(member);
    }
}
