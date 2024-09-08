package com.hj.timebean.controller;

import com.hj.timebean.dto.UpdateDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
        System.out.println(accountId);
        System.out.println(file.getOriginalFilename());
        try {
             profileImg = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("File size: " + file.getSize());
        System.out.println("Content type: " + file.getContentType());

        memberService.updateProfileImg(accountId,profileImg);
        return "redirect:/myPage";
    }


}
