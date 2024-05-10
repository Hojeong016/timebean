package com.hj.timebean.dto;


import com.hj.timebean.entity.Member;

import javax.validation.constraints.NotBlank;

public class SignInDTO{

    @NotBlank(message = "로그인 ID가 작성되지 않았습니다.")
    private String memberId;
    @NotBlank(message = "로그인 password가 작성되지 않았습니다.")
    private String password;

    public SignInDTO() {
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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
