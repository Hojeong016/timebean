package com.hj.timebean.service.ranking;

import com.hj.timebean.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingSchedulerService {
    private final RankingService rankingService;
    private final RankingRepository rankingRepository;

    @Autowired
    public RankingSchedulerService(RankingService rankingService, RankingRepository rankingRepository) {
        this.rankingService = rankingService;
        this.rankingRepository = rankingRepository;
    }

    @Scheduled(fixedRate = 60000) // 1분마다 실행
    public void refreshTop100Cache() {
        System.out.println("캐시를 삭제하고 새로 갱신 중...");

        // DB에서 데이터를 가져와 캐시 갱신
        List<Object[]> results = rankingRepository.findAllRankingsWithRank();
        rankingService.updateRankingsWithCachePut(results);
    }

}
