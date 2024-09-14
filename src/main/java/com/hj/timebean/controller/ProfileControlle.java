package com.hj.timebean.controller;

import com.hj.timebean.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileControlle {

    private final MemberService memberService;

    public ProfileControlle(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile/view")
    public String profileView(Model model, Principal principal) {
        return memberService.getMemberPicture(principal.getName());
    }
}
