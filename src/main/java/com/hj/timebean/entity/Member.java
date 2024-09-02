package com.hj.timebean.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //일반 로그인 유저 pk
    @Column(name = "login_id")
    private int loginId;

    @Column(name = "oauth_id")
    //oauth 로그인 유저 pk
    private int oauthId;

    //공동 정보
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "email")
    private String email;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "timer_password")
    private int timerPassword;
    @Column(name = "role")
    private String role; //ROLE_USER, ROLE_ADMIN
    @Column(name = "level")
    private String level; //"씨앗"

    //읿반 호그인 속성
    private String password;
    private Timestamp lastLogin;

    // @Pattern(regexp = "@") // 정규표현식

    //oauth 로그인 속성
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_id")
    private String providerId;


}
