package com.hj.timebean.dto;

import java.time.LocalDate;

// 랭크를 표시하기 위한 DTO
public class MemberRankDTO {
    private Long id;
    private Long memberId;
    private String nickname;
    private int totalTime;
    private LocalDate recordedDate;
    private int rank;

    public MemberRankDTO(Long id, Long memberId, String nickname, int totalTime, LocalDate recordedDate, int rank) {
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.totalTime = totalTime;
        this.recordedDate = recordedDate;
        this.rank = rank;
    }

    public MemberRankDTO(Long id, Long memberId, int totalTime, LocalDate recordedDate, int rank) {
        this.id = id;
        this.memberId = memberId;
        this.totalTime = totalTime;
        this.recordedDate = recordedDate;
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
