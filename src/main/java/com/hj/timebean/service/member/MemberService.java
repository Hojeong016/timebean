package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.entity.Ranking;

public interface MemberService {
    // 회원가입
    Member signUp(SignUpDTO signUpDTO);
    // 아이디 중복 검사
    void existsByAccountId(SignUpDTO signUpDTO);
    // 닉네임 중복 검사
    void existsByNickname(SignUpDTO signUpDTO);
    // 이메일 중복 검사
    void existsByEmail(SignUpDTO signUpDTO);
}