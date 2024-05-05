package com.hj.timebean.service.ranking;

import com.hj.timebean.entity.Ranking;

import java.time.LocalDate;
import java.util.List;

public interface RankingService {

    public List<Ranking> findByRegDate();
    public List<Ranking> getTop3Ranking(List<Ranking> rankingList);
}
