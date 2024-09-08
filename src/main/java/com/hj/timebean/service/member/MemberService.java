package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;

public interface MemberService {

    Member findByAccountId(String accountId);
    // 회원가입
    Member signUp(SignUpDTO signUpDTO);
    //회원 정보 수정
    void update(UpdateDTO updateDTO);
    void updateProfileImg(String accountId,byte[] profileImg);
    // 아이디 중복 검사
    void existsByAccountId(SignUpDTO signUpDTO);
    // 닉네임 중복 검사
    void existsByNickname(SignUpDTO signUpDTO);
    // 이메일 중복 검사
    void existsByEmail(SignUpDTO signUpDTO);
}