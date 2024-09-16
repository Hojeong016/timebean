package org.hj.timebean.controller;

import org.hj.timebean.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/member")
public class ProfileController {

    private final MemberService memberService;

    public ProfileController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/profile/view")
    public String profileView(Model model, Principal principal) {
        String profile = memberService.getMemberPicture(principal.getName());
        System.out.println("profile ="+ profile);
        model.addAttribute("profile", profile);
        return "userPage/myPage";
    }
}
