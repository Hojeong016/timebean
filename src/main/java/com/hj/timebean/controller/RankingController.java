package com.hj.timebean.controller;

import com.hj.timebean.auth.PrincipalDetails;
import com.hj.timebean.entity.Ranking;
import com.hj.timebean.repository.MemberRepository;
import com.hj.timebean.service.member.MemberService;
import com.hj.timebean.service.ranking.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            Ranking rankingDetails = rankingService.findByMemberId(principalDetails.getMember().getId());

            if (rankingDetails == null) {
                rankingDetails = new Ranking();
            }

            model.addAttribute("memberDetails", principalDetails.getMember());
            model.addAttribute("rankingDetails", rankingDetails);
        }

        // 오늘 날짜에 해당하는 랭킹 목록 조회
        List<Ranking> todayRankingList = rankingService.findByRecordedDate();

        //total time 기준으로 내림차순 정렬
        //Collections.sort(todayRankingList);
        List<Ranking> topTenRankingList = rankingService.getTopTenRanking(todayRankingList);

        // 모델에 데이터 전달
        model.addAttribute("todayRankingList", topTenRankingList);

        // Thymeleaf 템플릿의 경로 반환 (templates 폴더 내의 HTML 파일 경로)
        return "ranking/today_ranking";
    }
}
