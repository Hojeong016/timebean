package com.hj.timebean.token;
//스프링 시큐리티(Security) 모듈에서 제공되는 필터, HTTP 요청을 처리할 때 한 번만 실행되도록 보장,필터 체인 내에서 중복 실행을 방지
import com.hj.timebean.service.member.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//JwtAuthenticationFilter는 JWT 토큰으로 '인증(Authentication)'하고 SecurityContextHolder에 추가하는 필터를 설정하는 클래스.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final String secretKey;

    public JwtAuthenticationFilter(MemberService memberService, String secretKey) {
        super();
        this.memberService = memberService;
        this.secretKey = secretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
