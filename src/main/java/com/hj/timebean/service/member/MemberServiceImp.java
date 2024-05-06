package com.hj.timebean.service.member;

import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입 기능
    @Override
    public Member signUp(Member member) {
        //아이디 중복체크 메서드
        return memberRepository.save(member);
    }

    //로그인 기능 (로그인 성공 시 true를 반환)
    @Override
    public Boolean login(SignInDTO signInDTO) {

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
