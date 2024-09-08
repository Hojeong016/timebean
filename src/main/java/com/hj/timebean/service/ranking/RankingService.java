package com.hj.timebean.service.ranking;

import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;

import java.util.List;

public interface RankingService {
    // member_id로 랭킹 정보 가져오기
    Ranking findByMemberId(Long memberId);
    // 해당 날짜로 랭킹 데이터 가져오기
    public List<Ranking> findByRecordedDate();
    // 해당 개수만큼만 랭킹 데이터 가져오기
    public List<Ranking> getTopTenRanking(List<Ranking> rankingList);
    // 사용자의 랭크 순위를 가져오기
    public List<MemberRankDTO> getAllRankingsWithRank();
}
