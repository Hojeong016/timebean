package com.hj.timebean.service.ranking;

import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;
import com.hj.timebean.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "rankings")
public class RankingServiceImpl implements RankingService{
    private final RankingRepository rankingRepository;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository) {
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Ranking findByMemberId(Long memberId) {
        return rankingRepository.findByMemberId(memberId);
    }

    @Override
    public List<Ranking> findByRecordedDate() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();

        return rankingRepository.findByRecordedDate(today);
    }

    @Override
    @Cacheable(key = "'allRankings'", unless = "#result == null || #result.isEmpty()")
    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }

    // 랭킹 데이터를 서버에서 가공
    @Override
    public List<Ranking> getTopHundredRanking(List<Ranking> rankingList) {
        Collections.sort(rankingList);

        return rankingList.stream().limit(100).collect(Collectors.toList());
//        return rankingList.stream()
//                .sorted(Comparator.comparingInt(Ranking::getTotalTime).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
    }

    @Override
    @CachePut(value = "allTodayRankings", key = "'allTodayRankings'")
    public List<MemberRankDTO> updateRankingsWithCachePut(List<Object[]> results) {
        System.out.println("DB에서 데이터를 가져와 캐시에 저장 중...");

        List<MemberRankDTO> memberRankDTOList = new ArrayList<>();
        for (Object[] result : results) {
            memberRankDTOList.add(new MemberRankDTO(
                    (Long) result[0], (Long) result[1], (String) result[2],
                    (Integer) result[3], ((java.sql.Date) result[4]).toLocalDate(),
                    ((Number) result[5]).intValue()));
        }

        return memberRankDTOList;
    }

    // 주기적으로 DB에서 데이터를 가져와 캐시에 저장하는 로직
    @Override
    //@Cacheable(key = "'allTodayRankings'", unless = "#result == null || #result.isEmpty()")
    @CachePut(value = "allTodayRankings", key = "'allTodayRankings'")
    public List<MemberRankDTO> getAllRankingsWithRank() {
        System.out.println("db에서 불러오는 중..");

        List<Object[]> results = rankingRepository.findAllRankingsWithRank();
        List<MemberRankDTO> memberRankDTO = new ArrayList<>();

        for (Object[] result : results) {
            memberRankDTO.add(new MemberRankDTO(
                    (Long) result[0],                           // id
                    (Long) result[1],                           // m.id (member_id)
                    (String) result[2],                         // nickname
                    (Integer) result[3],                        // totalTime
                    ((java.sql.Date) result[4]).toLocalDate(),  // recordedDate (변환 처리)
                    ((Number) result[5]).intValue()             // rank
            ));
        }

        return memberRankDTO;
    }
}
