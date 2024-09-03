package com.hj.timebean.controller;

import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.service.member.MemberService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final MemberService memberService;

    public SignUpController(MemberService memberService){
        this.memberService = memberService;
    }
    //회원가입
    @GetMapping("/signUpView")
    public String signUpView(Model model){
        model.addAttribute("signUpDTO", new SignUpDTO());

        return "signUp/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid @ModelAttribute("signUpDTO") SignUpDTO signUpDTO, BindingResult bindingResult, Model model){
        try {
            // 아이디, 닉네임, 이메일 중복 검사
            memberService.existsByAccountId(signUpDTO);
            memberService.existsByNickname(signUpDTO);
            memberService.existsByEmail(signUpDTO);

            if (bindingResult.hasErrors()) {
                return "signUp/signUp";
            }
            memberService.signUp(signUpDTO);

            return "redirect:/";
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "signUp/signUp"; // 예외가 발생했을 때 다시 회원가입 페이지로 이동
        }
    }
}
