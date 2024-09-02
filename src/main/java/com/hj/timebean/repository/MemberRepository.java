package com.hj.timebean.repository;

import com.hj.timebean.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Member findByAccountId(String accountId); // Jpa query method

    // 아이디 중복검사
    boolean existsByAccountId(String memberID);
    // 닉네임 중복검사
    boolean existsByNickname(String nickname);
    // email 중복검사
    boolean existsByEmail(String email);
    //findBy 규칙 -> Username 문법
    // select * from user where username = ?
    //jpa name 함수

}
