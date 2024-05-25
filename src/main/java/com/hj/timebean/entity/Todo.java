package com.hj.timebean.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "text")
    private String text;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "status")
    private boolean status;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        regDate = LocalDate.now();
    }

    public Todo() {
    }

    public Todo(Long id, Member member, String text, boolean completed, boolean status, LocalDate regDate) {
        this.id = id;
        this.member = member;
        this.text = text;
        this.completed = completed;
        this.status = status;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}