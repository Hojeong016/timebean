package org.hj.timebean.repository;

import org.hj.timebean.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Optional<Member> findById(Long id);
    Member findByAccountId(String accountId); // Jpa query method
    //사용자 정의 쿼리
    @Modifying
    @Query("UPDATE Member SET  timerPassword = :timerPassword, updatedDate =:updateTime WHERE accountId = :accountId")
    int updateTimerPassword(
            @Param("accountId") String accountId,
           @Param("timerPassword") int timerPassword,
           @Param("updateTime") LocalDate updateTime);



    @Modifying
    @Query("UPDATE Member SET profileImage = :profileImg WHERE accountId = :accountId")
    void updateProfileImg(@Param("accountId") String accountId,
                          @Param("profileImg") byte[] profileImg);
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
