package com.hj.timebean.dto;

import com.hj.timebean.MemberRole;

public class SignUpDTO {
    private String memberId;
    private String password;
    private String nickname;
    private MemberRole role;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                '}';
    }
}
