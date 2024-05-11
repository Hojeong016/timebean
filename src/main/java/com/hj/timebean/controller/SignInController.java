package com.hj.timebean.controller;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.service.member.MemberService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String signIn(@Valid @ModelAttribute SignInDTO signInDTO, RedirectAttributes redirectAttributes) {

        if (StringUtils.isBlank(signInDTO.getMemberId()) || StringUtils.isBlank(signInDTO.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "모든 정보를 입력해주세요.");
            return "redirect:/signIn/signInView";
        }


            boolean result = memberService.login(signInDTO);

            if (result == true) {
                //랭킹창 반환
                return "redirect:/";
            } else {
                //로그인 창 반환
                return "signIn/signIn";
            }
        }

}
