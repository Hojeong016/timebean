package com.hj.timebean.controller;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final MemberService memberService;

    @Autowired
    public SignUpController(MemberService memberService){
        this.memberService = memberService;
    }
    //회원가입
    @GetMapping("/signUpView")
    public String signUpView(){
        return "signUp/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpDTO signUpDTO){
        memberService.signUp(signUpDTO);
        return "redirect:/";
    }



}
