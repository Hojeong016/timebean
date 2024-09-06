package com.hj.timebean.repository;

import com.hj.timebean.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {

    Member findByAccountId(String accountId); // Jpa query method

    //사용자 정의 쿼리
    @Modifying
    @Query("UPDATE Member SET  email = :email, password = :password, nickname = :nickname, timerPassword = :timerPassword, updatedDate =:updateTime WHERE accountId = :accountId")
    int updateAllBy(
            @Param("accountId") String accountId,
                       @Param("email") String email,
                       @Param("password") String password,
                       @Param("nickname") String nickname,
                       @Param("timerPassword") int timerPassword,
                       @Param("updateTime") LocalDate updateTime);
    // 아이디 중복검사
    boolean existsByAccountId(String accountId);
    // 닉네임 중복검사
    boolean existsByNickname(String nickname);
    // email 중복검사
    boolean existsByEmail(String email);
    //findBy 규칙 -> Username 문법
    // select * from user where username = ?
    //jpa name 함수
}
