package com.hj.timebean.service.ranking;

import com.hj.timebean.entity.Ranking;

import java.util.List;

public interface RankingService {

    public List<Ranking> findByRecordedDate();
    public List<Ranking> getTop3Ranking(List<Ranking> rankingList);
}
