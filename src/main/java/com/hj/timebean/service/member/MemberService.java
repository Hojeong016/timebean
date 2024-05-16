package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Optional;

public interface MemberService {

    //회원가입
    Member signUp(SignUpDTO signUpDTO);
    //로그인
    Boolean login(SignInDTO signInDTO);
    //로그아웃
    void logout();
    //회원정보조회
    Member findByMemberId(String memberId);
}
