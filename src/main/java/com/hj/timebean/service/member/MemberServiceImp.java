package com.hj.timebean.service.member;

import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.repository.MemberRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceImp(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입 기능
    @Override
    public Member signUp(Member member) {
        //아이디 중복 체크 메서드
        return memberRepository.save(member);
    }

    //로그인 기능 (로그인 성공 시 true를 반환)
    @Override
    public Boolean login(SignInDTO signInDTO){

        Member member = memberRepository.findByMemberId(signInDTO.getMemberId());

        if(member != null && signInDTO.getPassword().equals(member.getPassword())){
            System.out.println("로그인 성공");
            return true;
        }else {
            System.out.println("로그인 실패");
            return false;
        }
        //추후 솔트 적용된 패스워드 검증 메서드 추가하여 호출하는 방식으로 로그인 검증하기
    }

    //로그아웃 기능
    @Override
    public void logout() {

    }

    //회원 조회 기능
    @Override
    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }
}
