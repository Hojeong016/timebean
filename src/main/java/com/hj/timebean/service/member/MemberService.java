package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    // 회원가입
    Member signUp(SignUpDTO signUpDTO);
    // 로그인
    Boolean login(SignInDTO signInDTO);
    // 로그아웃
    void logout();
    // 회원정보조회
    UserDetails findByMemberId(String memberId);
    // 아이디 중복 검사
    void existsByMemberId(SignUpDTO signUpDTO);
    // 닉네임 중복 검사
    void existsByNickname(SignUpDTO signUpDTO);
    // 이메일 중복 검사
    void existsByEmail(SignUpDTO signUpDTO);
}
