package com.example.whycors.domain.member;

import com.example.whycors.domain.member.dto.SignupRequestDto;
import com.example.whycors.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "profileImageUrl", unique = true)
    private String profileImageUrl;

    @Column(name = "phoneNumber", unique = true)
    private String phoneNumber;

    @Column(name = "gender", unique = true)
    private String gender;



    @Column
    @Enumerated(value = EnumType.STRING) // 정보를 받을 때는 Enum 값으로 받지만
    private UserRole role;

    public Member(SignupRequestDto requestDto, String password) {
        this.username = requestDto.getUsername();
        this.password = password;
        this.nickname = requestDto.getNickname();
        this.role = UserRole.USER;
        this.profileImageUrl = null;
        this.phoneNumber = requestDto.getPhoneNumber();
        this.gender = requestDto.getGender();
    }

}
