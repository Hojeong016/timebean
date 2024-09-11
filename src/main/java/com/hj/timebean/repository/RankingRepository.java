package com.hj.timebean.repository;

import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByMemberId(Long memberId);
    List<Ranking> findByRecordedDate(LocalDate recordedDate);
    List<Ranking> findAll();

    // Top 100명의 랭킹을 조회하는 쿼리
    @Query(value = "SELECT r.id, m.id as member_id, m.nickname, r.total_time, r.recorded_date, " +
            "DENSE_RANK() OVER (ORDER BY r.total_time DESC) as 'rank' " +
            "FROM ranking r " +
            "JOIN member m ON r.member_id = m.id limit 100", nativeQuery = true)
    List<Object[]> findAllRankingsWithRank();

    // 특정 사용자의 랭킹을 조회하는 쿼리, 전체 검색으로 인한 성능 저하로 인해 사용 안함.
    @Query(value = "SELECT * FROM ( " +
            "  SELECT r.id, m.id as member_id, m.nickname, r.total_time, r.recorded_date, " +
            "         DENSE_RANK() OVER (ORDER BY r.total_time DESC) as 'rank' " +
            "  FROM ranking r " +
            "  JOIN member m ON r.member_id = m.id " +
            ") AS ranked_data " +
            "WHERE ranked_data.member_id = :memberId", nativeQuery = true)
    List<Object[]> findUserRank(@Param("memberId") Long memberId);

    // 특정 사용자의 랭킹을 조회하는 쿼리, where 조건 후 rank가 집계되어서 순위가 1만 나오는 문제 발생.
    @Query("SELECT new com.hj.timebean.dto.MemberRankDTO(r.id, m.id, m.nickname, r.totalTime, r.recordedDate, " +
            "DENSE_RANK() OVER (ORDER BY r.totalTime DESC)) " +
            "FROM Ranking r " +
            "JOIN r.member m " +
            "WHERE m.id = :memberId")
    MemberRankDTO findUserRank2(@Param("memberId") Long memberId);


}
