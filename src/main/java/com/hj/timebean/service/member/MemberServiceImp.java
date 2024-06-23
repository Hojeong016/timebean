package com.hj.timebean.service.member;

import com.hj.timebean.OAuth.Role;
import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.dto.SignInDTO;
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
        boolean isExist = memberRepository.existsByMemberId(memberId);

        if (isExist){
            System.out.println("존재하는 아이디 입니다.");
        }

        Member data = new Member();
        data.setMemberId(memberId);
        data.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        data.setNickname(signUpDTO.getNickname());
        data.setEmail(signUpDTO.getEmail());
        data.setRole(Role.ROLE_USER);

        return memberRepository.save(data);
    }

    // 로그인 기능 (로그인 성공 시 true를 반환)
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

    // 로그아웃 기능
    @Override
    public void logout() {

    }

    // 회원 조회 기능
    @Override
    public Member findByMemberId(String memberId) throws UsernameNotFoundException {

        return memberRepository.findByMemberId(memberId);
    }

    // 아이디 중복 검사
    @Override
    public void existsByMemberId(SignUpDTO signUpDTO) {
        boolean memberIdDuplicate = memberRepository.existsByMemberId(signUpDTO.getMemberId());
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
