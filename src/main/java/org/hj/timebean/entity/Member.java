package org.hj.timebean.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "member")
public class Member implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;  // 고정된 serialVersionUID 설정

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "updated_date")
    private LocalDate updatedDate;

    @Lob
    @Column(name = "profile_image")
    private byte[] profileImage;

    @Column(name = "profile_url")
    private String profileUrl;

    //읿반 로그인 속성
    private String password;
    private LocalDate last_login;

    // @Pattern(regexp = "@") // 정규표현식

    //oauth 로그인 속성
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_id")
    private String providerId;

    @PrePersist //엔티티가 영속화되기 전에 호출
    public void prePersist() {
        createdDate = LocalDate.now();
    }
}
