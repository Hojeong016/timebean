package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.repository.MemberRepository;
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


    // 회원 가입 기능
    @Override
    public Member signUp(SignUpDTO signUpDTO) {

        String memberId = signUpDTO.getMemberId();
        boolean isExist = memberRepository.existsByAccountId(memberId);

        if (isExist){
            System.out.println("존재하는 아이디 입니다.");
        }

        Member data = new Member();
        data.setAccountId(memberId);
        data.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        data.setNickname(signUpDTO.getNickname());
        data.setEmail(signUpDTO.getEmail());
        data.setRole("ROLE_USER");

        return memberRepository.save(data);
    }



    // 아이디 중복 검사
    @Override
    public void existsByMemberId(SignUpDTO signUpDTO) {
        boolean memberIdDuplicate = memberRepository.existsByAccountId(signUpDTO.getMemberId());
        if (memberIdDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    // 닉네임 중복 검사
    @Override
    public void existsByNickname(SignUpDTO signUpDTO) {
        boolean nicknameDuplicate = memberRepository.existsByNickname(signUpDTO.getNickname());
        if (nicknameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    // 이메일 중복 검사
    @Override
    public void existsByEmail(SignUpDTO signUpDTO) {
        boolean emailDuplicate = memberRepository.existsByEmail(signUpDTO.getEmail());
        if (emailDuplicate) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
}
