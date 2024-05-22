package com.hj.timebean.repository;

import com.hj.timebean.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //.save() 반환 타입 엔티티
    Member save(Member member);
    //Optional<Member> findById(Long id);
    Member findByMemberId(String memberId);
    // 아이디 중복검사
    boolean existsByMemberId(String memberID);
    // 닉네임 중복검사
    boolean existsByNickname(String nickname);
    // email 중복검사
    boolean existsByEmail(String email);

}
