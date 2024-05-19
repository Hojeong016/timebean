package com.hj.timebean.service.member;

import com.hj.timebean.MemberRole;
import com.hj.timebean.dto.CustomMemberDetails;
import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImp implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberServiceImp(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //회원 가입 기능
    @Override
    public Member signUp(SignUpDTO signUpDTO) {

        String memberId = signUpDTO.getMemberId();
        boolean isExist = memberRepository.existsByMemberId(memberId);

        if (isExist){
            System.out.println("존재하는 아이디 입니다.");
        }

        Member data = new Member();
        data.setMemberId(memberId);
        data.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        data.setNickname(signUpDTO.getNickname());
        data.setRole("ROLE_ADMIN");

        return memberRepository.save(data);
    }

    //로그인 기능 (로그인 성공 시 true를 반환)
    @Override
    public Boolean login(SignInDTO signInDTO){

        Member member = memberRepository.findByMemberId(signInDTO.getMemberId());

        if(member != null && bCryptPasswordEncoder.matches(signInDTO.getPassword(), member.getPassword())){
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
    public UserDetails findByMemberId(String memberId) throws UsernameNotFoundException {

       Member memberData = memberRepository.findByMemberId(memberId);

       if(memberData != null){

           return new CustomMemberDetails(memberData);
       }
        return null;
    }
}
