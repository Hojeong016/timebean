package com.hj.timebean.dto;


public class SignInDTO {

    private String memberId;
    private String password;
    private String nickname;

    public SignInDTO(String memberId,String password,String nickname){
        this.memberId = memberId;
        this.password =password;
        this.nickname =nickname;
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

    @Override
    public String toString() {
        return "SignInDTO{" +
                "memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
