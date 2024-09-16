package org.hj.timebean.service.member;

import org.hj.timebean.dto.SignUpDTO;
import org.hj.timebean.dto.UpdateDTO;
import org.hj.timebean.entity.Member;

import java.security.Principal;
import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long id);
    Member findByAccountId(String accountId);
    String getMemberPicture(String accountId);
    // 회원가입
    Member signUp(SignUpDTO signUpDTO);
    //회원 정보 수정
    void update(UpdateDTO updateDTO, Principal principal);
    void timerUpdate(int password, Principal principal);
    void updateProfileImg(String accountId,byte[] profileImg);
    // 아이디 중복 검사
    void existsByAccountId(SignUpDTO signUpDTO);
    // 닉네임 중복 검사
    void existsByNickname(SignUpDTO signUpDTO);
    // 이메일 중복 검사
    void existsByEmail(SignUpDTO signUpDTO);
}