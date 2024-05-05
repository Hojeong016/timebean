package com.hj.timebean.service;

import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.LoginDTO;
import com.hj.timebean.dto.SignInDTO;

public class MemberServiceImp implements MemberService{

    //회원 가입 기능
    @Override
    public Member signIn(SignInDTO signInDTO) {
        return null;
    }

    //로그인 기능 (로그인 성공 시 true를 반환)
    @Override
    public Boolean login(LoginDTO loginDTO) {

        return null;
    }

    //로그아웃 기능
    @Override
    public void logout() {

    }

    //회원 조회 기능
    @Override
    public Member findByMemberId(String memberId) {
        return null;
    }
}
