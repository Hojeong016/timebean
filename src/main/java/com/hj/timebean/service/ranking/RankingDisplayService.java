package com.hj.timebean.service.ranking;

import com.hj.timebean.dto.MemberRankDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingDisplayService {

    // 캐시를 조회해서 컨트롤러에 반환하고 없으면 null 반환
    @Cacheable(value = "allTodayRankings", key = "'allTodayRankings'")
    public List<MemberRankDTO> getCachedRankings(){
        System.out.println("캐시에서 랭킹 조회 중...");
        // 캐시에 저장된 데이터를 반환 (캐시가 없으면 자동으로 빈 리스트 또는 null 반환)
        return null;
    }
}
