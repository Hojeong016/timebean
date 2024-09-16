package org.hj.timebean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@JsonManagedReference // 순환 참조 방지
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore // 이 필드를 직렬화에서 제외
    private Member member;

    @Column(name = "text")
    private String text;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "status")
    private boolean status;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        recordedDate = LocalDate.now();
    }

    public Todo() {
    }

    public Todo(Long id, Member member, String text, boolean completed, boolean status, LocalDate recordedDate) {
        this.id = id;
        this.member = member;
        this.text = text;
        this.completed = completed;
        this.status = status;
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

    public LocalDate getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDate recordedDate) {
        this.recordedDate = recordedDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", member=" + member +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", status=" + status +
                ", recordedDate=" + recordedDate +
                '}';
    }
}
