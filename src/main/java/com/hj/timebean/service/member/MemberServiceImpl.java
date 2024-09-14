package com.hj.timebean.service.member;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member findByAccountId(String accountId) {
        Member member = memberRepository.findByAccountId(accountId);
        return member;
    }

    //프로필 이미지 가져오기
    @Override
    public String getMemberPicture(String accountId) {
        Member member = memberRepository.findByAccountId(accountId);

        byte[] profile = member.getProfileImage();
        //Oauth 유저와 기본 프로필 유저일 경우 프로필 형식이 URL로 db에 저장 되있을 수 있다.
        if (profile != null) {
            return Base64.getEncoder().encodeToString(profile);
        } else {
            return member.getProfileUrl();
        }
    }
    // 회원 가입 기능
    @Override
    public Member signUp(SignUpDTO signUpDTO) {

        String accountId = signUpDTO.getAccountId();
        boolean isExist = memberRepository.existsByAccountId(accountId);

        if (isExist){
            System.out.println("존재하는 아이디 입니다.");
        }

        Member data = new Member();
        data.setAccountId(accountId);
        data.setPassword(bCryptPasswordEncoder.encode(signUpDTO.getPassword()));
        data.setNickname(signUpDTO.getNickname());
        data.setEmail(signUpDTO.getEmail());
        data.setTimerPassword(Integer.parseInt(signUpDTO.getTimerPassword()));
        data.setLevel("씨앗");
        data.setRole("ROLE_USER");

        return memberRepository.save(data);
    }

    //일반 로그인 회원 수정
    @Transactional
    @Override
    public void update(UpdateDTO updateDTO) {
        //인증 객체에 담겨있는 아이디를 꺼내 변수에 저장 getName();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        String encoderPassword = bCryptPasswordEncoder.encode(updateDTO.getPassword());

        int result = memberRepository.updateAllBy(username,
                    updateDTO.getEmail(),
                    encoderPassword,
                    updateDTO.getNickname(),
                    updateDTO.getTimerPassword(),
                    LocalDate.now());
    }

    @Transactional
    @Override
    public void updateProfileImg(String accountId,byte[] profileImg) {
        System.out.println("accountId : " + accountId + " profileImg : " + profileImg);
    memberRepository.updateProfileImg(accountId,profileImg);
    }

    // 아이디 중복 검사
    @Override
    public void existsByAccountId(SignUpDTO signUpDTO) {
        boolean accountIdDuplicate = memberRepository.existsByAccountId(signUpDTO.getAccountId());
        if (accountIdDuplicate) {
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
