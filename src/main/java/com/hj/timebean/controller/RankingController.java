package com.hj.timebean.controller;

import com.hj.timebean.entity.Ranking;
import com.hj.timebean.service.ranking.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
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
    public String showTodayRanking(Model model) {

        // 오늘 날짜에 해당하는 랭킹 목록 조회
        List<Ranking> todayRankingList = rankingService.findByRegDate();

        //total time 기준으로 내림차순 정렬
        //Collections.sort(todayRankingList);
        List<Ranking> top3RankingList = rankingService.getTop3Ranking(todayRankingList);

        // 모델에 데이터 전달
        model.addAttribute("todayRankingList", top3RankingList);

        // Thymeleaf 템플릿의 경로 반환 (templates 폴더 내의 HTML 파일 경로)
        return "ranking/today_ranking";
    }
}
