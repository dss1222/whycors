package com.example.whycors.global.exceptionhandler;

import lombok.Data;

@Data
public class ErrorMessage {
    static public String notCreator = "게시글 작성자가 아닙니다";
    static public String notPosts = "해당 게시글이 존재하지 않습니다";
    static public String duplicatedMemberId = "중복된 사용자 ID가 존재합니다";
    static public String duplicatedNickname = "중복된 사용자 닉네임이 존재합니다";
    static public String duplicatedPassword = "비밀번호 확인이 일치하지 않습니다";

}
