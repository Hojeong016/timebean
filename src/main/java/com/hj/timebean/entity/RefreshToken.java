package com.hj.timebean.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Table(name = "refresh_token")
@Entity
public class RefreshToken {
    //MemberId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "member_member_id")
    private String memberMemberId;
    //Refresh Token String 이
    @Column(name = "value")
    private String value;

    public RefreshToken updateValue(String token) {
        this.value = token;
        return this;
    }

    public RefreshToken() {
    }

    @Builder
    public RefreshToken(Long id, String memberMemberId, String value) {
        this.id = id;
        this.memberMemberId = memberMemberId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberMemberId() {
        return memberMemberId;
    }

    public void setMemberMemberId(String memberMemberId) {
        this.memberMemberId = memberMemberId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
