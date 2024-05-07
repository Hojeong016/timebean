package com.hj.timebean.controller;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signIn")
public class SignInController {

    private final MemberService memberService;

    @Autowired
    public SignInController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/signInView")
    public String signInView(){
        return "signIn/signIn";
    }
    @PostMapping
    public String signIn(@ModelAttribute SignInDTO signInDTO) {

        boolean result = memberService.login(signInDTO);

        if (result = true) {
            //랭킹창 반환
            return"redirect:/";
        } else {
            //로그인 창 반환
            return"signIn/signIn";
        }
    }
}
