package com.hj.timebean.service.ranking;

import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;

import java.util.List;
import java.util.Optional;

public interface RankingService {
    // member_id로 랭킹 정보 가져오기
    Optional<Ranking> findByMemberId(Long memberId);
    // 해당 날짜로 랭킹 데이터 가져오기
    public List<Ranking> findByRecordedDate();
    // 모든 랭킹 데이터 조회
    public List<Ranking> findAll();
    // db에서 조회한 랭킹 데이터를 캐시에 저장하는 역할
    public List<MemberRankDTO> updateRankingsWithCachePut(List<Object[]> results);
    // 테스트용) 전체 랭크 가져오기
    public List<MemberRankDTO> getAllRankingsWithRank();
    //
    public Optional<MemberRankDTO> getUserRank(Long memberId);
    // 특정 사용자의 랭킹을 조회
    public Optional<MemberRankDTO> getUserRank2(Long memberId);
    // 해당 개수만큼만 랭킹 데이터 가져오기, 서버에서 데이터 가공, 현재 사용 안함
    public List<Ranking> getTopHundredRanking(List<Ranking> rankingList);
}
