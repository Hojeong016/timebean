package com.hj.timebean.controller;

import com.hj.timebean.entity.Ranking;
import com.hj.timebean.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RankingController {

    @Autowired
    private RankingRepository rankingRepository;

    @GetMapping("/")
    public String showTodayRanking(Model model) {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();

        // 오늘 날짜에 해당하는 랭킹 목록 조회
        List<Ranking> todayRankingList = rankingRepository.findByRegDate(today);

        // 모델에 데이터 전달
        model.addAttribute("todayRankingList", todayRankingList);

        // Thymeleaf 템플릿의 경로 반환 (templates 폴더 내의 HTML 파일 경로)
        return "ranking/today_ranking";
    }
}
