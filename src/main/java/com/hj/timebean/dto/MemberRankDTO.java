package com.hj.timebean.dto;

import java.io.Serializable;
import java.time.LocalDate;

// 랭크를 표시하기 위한 DTO
// Redis를 사용하여 데이터를 캐싱할 때, 캐시에 저장되는 객체는 직렬화(Serialization)가 가능해야 함.
public class MemberRankDTO implements Serializable {

    private static final long serialVersionUID = 1L; // 직렬화 버전 관리, 클래스의 구조가 변경될 때 동일한 serialVersionUID를 사용하면 기존 데이터와의 호환성을 유지할 수 있다.

    private Long id;
    private Long memberId;
    private String nickname;
    private int totalTime;
    private LocalDate recordedDate;
    private Long rank;

    public MemberRankDTO() {
    }

    public MemberRankDTO(Long id, Long memberId, String nickname, int totalTime, LocalDate recordedDate, Long rank) {
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.totalTime = totalTime;
        this.recordedDate = recordedDate;
        this.rank = rank;
    }

    public MemberRankDTO(Long id, Long memberId, int totalTime, LocalDate recordedDate, Long rank) {
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

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "MemberRankDTO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", nickname='" + nickname + '\'' +
                ", totalTime=" + totalTime +
                ", recordedDate=" + recordedDate +
                ", rank=" + rank +
                '}';
    }
}
