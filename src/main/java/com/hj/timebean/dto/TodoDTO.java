package com.hj.timebean.dto;

import java.time.LocalDate;

public class TodoDTO {
    private Long id;
    private Long memberId;
    private String text;
    private boolean completed;
    private boolean status;
    private LocalDate recordedDate;

    public TodoDTO() {
    }

    public TodoDTO(Long id, Long memberId, String text, boolean completed, boolean status, LocalDate recordedDate) {
        this.id = id;
        this.memberId = memberId;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
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
        return "TodoDTO{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", status=" + status +
                ", recordedDate=" + recordedDate +
                '}';
    }
}
