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

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        regDate = LocalDate.now();
    }

    public Ranking() {
    }

    public Ranking(String nickname, int totalTime, LocalDate regDate) {
        this.nickname = nickname;
        this.totalTime = totalTime;
        this.regDate = regDate;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int time) {
        this.totalTime = time;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public int compareTo(Ranking o) {
        return Integer.compare(o.getTotalTime(), this.getTotalTime());
    }
}
