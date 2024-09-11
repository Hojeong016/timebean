package com.hj.timebean.controller;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;
import com.hj.timebean.service.ranking.RankingDisplayService;
import com.hj.timebean.service.ranking.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class RankingController {
    private final RankingService rankingService;
    private final RankingDisplayService rankingDisplayService;

    @Autowired
    public RankingController(RankingService rankingService, RankingDisplayService rankingDisplayService) {
        this.rankingService = rankingService;
        this.rankingDisplayService = rankingDisplayService;
    }

    @GetMapping("/")
    public String showTodayRanking(Model model, Authentication authentication) {
        long startTime = System.currentTimeMillis(); // 코드 시작 시간

        // 캐시에서 랭킹 데이터를 조회
        List<MemberRankDTO> rankings = rankingDisplayService.getCachedRankings();

        if (rankings == null || rankings.isEmpty()) {
            System.out.println("캐시가 없어서 db에서 조회 중");
            // 상위 100명의 랭킹 조회
            rankings = rankingService.getAllRankingsWithRank();
        }

        model.addAttribute("memberRankList", rankings);

        // 사용자의 세션 정보가 있을 경우, 사용자 랭킹 정보 조회
        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Long memberId = principalDetails.getMember().getId();

            // 사용자 정보
            model.addAttribute("memberDetails", principalDetails.getMember());

            // 캐시에서 사용자 랭킹 조회
//            MemberRankDTO defaultRank = new MemberRankDTO(0L, memberId, principalDetails.getMember().getNickname(), 0, null, 0L);
//            MemberRankDTO memberRank = rankingService.getUserRank(memberId).orElseGet(() -> defaultRank);
            Optional<Ranking> myRankInfo = Optional.of(rankingService.findByMemberId(memberId).orElseGet(() ->
                    new Ranking(0L, principalDetails.getMember(), 0, null)));
            Optional<Integer> rank = rankingDisplayService.findMemberRankFromCache(memberId);

            // 나의 랭킹 정보
            myRankInfo.ifPresent(ranking -> model.addAttribute("myRankInfo", ranking));

            // 순위
            rank.ifPresent(ranking -> model.addAttribute("rank", ranking+"등"));
            if (rank.isEmpty()) {
                model.addAttribute("rank", "Top100에 들지 못하였습니다.");
            }
        }

        long endTime = System.currentTimeMillis(); // 코드 끝난 시간
        long durationTimeSec = endTime - startTime;
        System.out.println(durationTimeSec + "m/s"); // 밀리세컨드 출력
        System.out.println((durationTimeSec / 1000) + "sec"); // 초 단위 변환 출력

        return "ranking/today_ranking";
    }
}
