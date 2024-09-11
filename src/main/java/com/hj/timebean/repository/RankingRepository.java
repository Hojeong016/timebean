package com.hj.timebean.repository;

import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Ranking findByMemberId(Long memberId);
    List<Ranking> findByRecordedDate(LocalDate recordedDate);
    List<Ranking> findAll();

    @Query(value = "SELECT r.id, m.id as member_id, m.nickname, r.total_time, r.recorded_date, " +
            "DENSE_RANK() OVER (ORDER BY r.total_time DESC) as 'rank' " +
            "FROM ranking r " +
            "JOIN member m ON r.member_id = m.id limit 100", nativeQuery = true)
    List<Object[]> findAllRankingsWithRank();
}
