package com.hj.timebean;

public enum MemberRole {
    USER, ADMIN;
}

// USER - 사용자 ADMIN - 관리자
//로그인한 유저의 권한이 ADMIN이면 접속 가능
//로그인한 유저의 권한이 USER이면 홈으로 이동
//로그인하지 않은 유저가 접근 시 로그인 페이지로 이동
