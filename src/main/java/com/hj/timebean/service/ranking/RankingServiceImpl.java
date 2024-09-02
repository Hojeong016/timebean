package com.hj.timebean.service.ranking;

import com.hj.timebean.entity.Ranking;
import com.hj.timebean.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankingServiceImpl implements RankingService{

    private final RankingRepository rankingRepository;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public List<Ranking> findByRecordedDate() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();

        return rankingRepository.findByRecordedDate(today);
    }

    @Override
    public List<Ranking> getTop3Ranking(List<Ranking> rankingList) {
        Collections.sort(rankingList);

        return rankingList.stream().limit(3).collect(Collectors.toList());
//        return rankingList.stream()
//                .sorted(Comparator.comparingInt(Ranking::getTotalTime).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
    }
}
