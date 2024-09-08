package com.hj.timebean.controller;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.dto.MemberRankDTO;
import com.hj.timebean.entity.Ranking;
import com.hj.timebean.service.ranking.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class RankingController {
    private final RankingService rankingService;


    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/")
    public String showTodayRanking(Model model, Authentication authentication) {
        long startTime = System.currentTimeMillis(); // 코드 시작 시간

        List<MemberRankDTO> memberRankList = rankingService.getAllRankingsWithRank();
        // 마지막 등수보다 콩의 개수가 더 많으면 기존 캐시를 지우고 다시 db에서 조회
        System.out.println(memberRankList.get(99).getTotalTime());
        // 오늘 날짜에 해당하는 랭킹 목록 조회
//        List<Ranking> todayRankingList = rankingService.findByRecordedDate();
        // 모든 랭킹 데이터 조회
//        List<Ranking> rankingList = rankingService.findAll();
        //total time 기준으로 내림차순 정렬
//        Collections.sort(todayRankingList);
//        List<Ranking> topHundredRanking = rankingService.getTopHundredRanking(rankingList);

        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Ranking rankingDetails = rankingService.findByMemberId(principalDetails.getMember().getId());

            if (rankingDetails == null) {
                rankingDetails = new Ranking();
            }

            // 모델에 데이터 전달
            model.addAttribute("memberDetails", principalDetails.getMember());
            model.addAttribute("rankingDetails", rankingDetails);
        }

        // 모델에 데이터 전달
//        model.addAttribute("todayRankingList", topHundredRanking);
        model.addAttribute("memberRankList", memberRankList);

        long endTime = System.currentTimeMillis(); // 코드 끝난 시간

        long durationTimeSec = endTime - startTime;

        System.out.println(durationTimeSec + "m/s"); // 밀리세컨드 출력
        System.out.println((durationTimeSec / 1000) + "sec"); // 초 단위 변환 출력

        // Thymeleaf 템플릿의 경로 반환 (templates 폴더 내의 HTML 파일 경로)
        return "ranking/today_ranking";
    }
}
