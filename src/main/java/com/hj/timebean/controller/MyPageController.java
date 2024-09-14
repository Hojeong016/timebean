package com.hj.timebean.controller;

import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        Member member = memberService.findByAccountId(accountId);
        //html 에서 엔티티에 담긴 속성을 꺼내오기 위해 model 사용
        model.addAttribute("member", member);

        String profile = memberService.getMemberPicture(principal.getName());
        System.out.println("profile ="+ profile);
        model.addAttribute("profile", profile);

        return "userPage/myPage";
    }

    @PostMapping("/updateForm")
    public String myPage(UpdateDTO updateDTO){
    memberService.update(updateDTO);
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
