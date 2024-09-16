package org.hj.timebean.controller;

import org.hj.timebean.auth.PrincipalDetails;
import org.hj.timebean.repository.MemberRepository;
import org.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
public class SignInController {

        @Autowired
        private MemberService memberService;

        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        //일반 오어스 로그인 모두 다 한가지 타입으로 받아올 수 있다.
        @GetMapping("/user")
        public  @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
            return "user";
        }

        @GetMapping("/admin")
        public  @ResponseBody String admin(){
            return "admin";
        }

        //시큐리티의 인증 로그인이 낚아쳐버림... -> 작동하지 않게 기스에이블 해주었음
        @GetMapping("/signIn")
        public String loginFrom(){
            return "/signIn/signIn";
        }


    }
