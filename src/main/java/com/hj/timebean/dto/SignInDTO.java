package com.hj.timebean.dto;

import jakarta.validation.constraints.NotBlank;

public class SignInDTO{


    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
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
