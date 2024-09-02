package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;

public interface MemberService {
    // 회원가입
    Member signUp(SignUpDTO joinDTO);

    void existsByMemberId(SignUpDTO joinDTO);
    // 닉네임 중복 검사
    void existsByNickname(SignUpDTO joinDTO);
    // 이메일 중복 검사
    void existsByEmail(SignUpDTO joinDTO);
}