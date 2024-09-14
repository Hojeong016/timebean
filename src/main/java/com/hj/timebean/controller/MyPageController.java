package com.hj.timebean.controller;

import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
public class MyPageController {

    private final MemberService memberService;

    @Autowired
    public MyPageController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/myPage")
    public String userPage(Model model, Principal principal) {
        String accountId = principal.getName();
        //멤버의 프로필 이미지
        String profile = memberService.getMemberPicture(accountId);
        model.addAttribute("profile", profile);

        //멤버의 정보
        Member member = memberService.findByAccountId(accountId);
        //html 에서 엔티티에 담긴 속성을 꺼내오기 위해 model 사용
        model.addAttribute("member", member);
        return "userPage/myPage";
    }

    //기본 정보 수정
    @PostMapping("/updateForm")
    public String myPage( @ModelAttribute UpdateDTO updateDTO, Principal principal) {
        memberService.update(updateDTO,principal);
    return "redirect:/myPage";
    }

    //타이머 정보 수정 및 타이머 등록
    @PostMapping("updateTimer")
    public String timerUpdate(@RequestParam int timerPassword, Principal principal) {
       memberService.timerUpdate(timerPassword,principal);
        return "redirect:/myPage";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
        byte[] profileImg;
        String accountId = principal.getName();
        try {
             profileImg = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        memberService.updateProfileImg(accountId,profileImg);
        return "redirect:/myPage";
    }




}
