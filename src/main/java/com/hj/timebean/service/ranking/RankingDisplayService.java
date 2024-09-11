package com.hj.timebean.service.ranking;

import com.hj.timebean.dto.MemberRankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingDisplayService {

    @Autowired
    private ApplicationContext applicationContext;

    // 캐시를 조회해서 컨트롤러에 반환하고 없으면 null 반환
    @Cacheable(value = "allTodayRankings", key = "'allTodayRankings'")
    public List<MemberRankDTO> getCachedRankings(){
        System.out.println("캐시에서 랭킹 조회 중...");
        // 캐시에 저장된 데이터를 반환 (캐시가 없으면 자동으로 빈 리스트 또는 null 반환)
        return null;
    }

    public List<MemberRankDTO> getTop100Rankings() {
        RankingDisplayService rankingDisplayService = applicationContext.getBean(RankingDisplayService.class);
        List<MemberRankDTO> cachedRankings = rankingDisplayService.getCachedRankings();

        return cachedRankings.stream().limit(100).toList();
    }

    // 캐시에서 특정 사용자의 랭킹을 가져오는 기능
    public Optional<Integer> findMemberRankFromCache(Long memberId) {
        RankingDisplayService rankingDisplayService = applicationContext.getBean(RankingDisplayService.class);
        List<MemberRankDTO> cachedRankings = rankingDisplayService.getCachedRankings();

        if (cachedRankings == null || cachedRankings.isEmpty()) {
            System.out.println("캐시에 저장된 데이터가 없습니다.");
            return null;
        }
        System.out.println("캐시가 있음");
        // 캐시가 있을 경우
        for (int i = 0; i < cachedRankings.size(); i++) {
            MemberRankDTO memberRank = cachedRankings.get(i);
            if (memberRank.getMemberId().equals(memberId)) {
                return Optional.of(i + 1); // 순위 반환
            }
        }

        // 사용자가 순위에 없을 경우
        return Optional.empty();
    }
}
