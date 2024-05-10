package com.hj.timebean.controller;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.service.member.MemberService;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("signIn")
public class SignInRestController {
    private final MemberService memberService;
    public SignInRestController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping
    public ResponseEntity<String> signInBlank(@RequestBody SignInDTO signInDTO){
        //공란 검증
        if (StringUtils.isBlank(signInDTO.getMemberId()) || StringUtils.isBlank(signInDTO.getPassword())) {
            return ResponseEntity.badRequest().body("아이디 또는 패스워드가 입력되지 않았습니다.");
        }else {
            memberService.login(signInDTO);
            return ResponseEntity.ok().body("로그인 성공");
        }
    }
}
