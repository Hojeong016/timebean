package com.hj.timebean.service;

import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.LoginDTO;
import com.hj.timebean.dto.SignInDTO;

public interface MemberService {

    //회원가입
    Member signIn(SignInDTO signInDTO);
    //로그인
    Boolean login(LoginDTO loginDTO);
    //로그아웃
    void logout();
    //회원정보조회
    Member findByMemberId(String memberId);
}
