package com.hj.timebean.entity;

import com.hj.timebean.MemberRole;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "reg_date")
    private LocalDate regDate;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        regDate = LocalDate.now();
    }

    public Member(long id, String memberId, String password, String nickname, String email, String role, LocalDate regDate) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.regDate = regDate;
    }

    public Member() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role=" + role +
                ", regDate=" + regDate +
                '}';
    }
}
