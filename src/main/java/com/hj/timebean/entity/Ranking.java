package com.hj.timebean.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ranking")
public class Ranking implements Comparable<Ranking>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        recordedDate = LocalDate.now();
    }

    public Ranking() {
    }

    public Ranking(int totalTime, LocalDate recordedDate) {
        this.totalTime = totalTime;
        this.recordedDate = recordedDate;
    }

    public Ranking(Long id, Member member, int totalTime, LocalDate recordedDate) {
        this.id = id;
        this.member = member;
        this.totalTime = totalTime;
        this.recordedDate = recordedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int time) {
        this.totalTime = time;
    }

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", member=" + member +
                ", totalTime=" + totalTime +
                ", recordedDate=" + recordedDate +
                '}';
    }

    @Override
    public int compareTo(Ranking o) {
        return Integer.compare(o.getTotalTime(), this.getTotalTime());
    }
}
