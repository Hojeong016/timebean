package com.hj.timebean.controller;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.dto.TokenDTO;
import com.hj.timebean.service.jwt.JwtAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtAuthService authService;

    @Autowired
    public AuthController(JwtAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpDTO> signup(@RequestBody SignUpDTO memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody SignInDTO memberRequestDto) {
        System.out.println(memberRequestDto);
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@RequestBody TokenDTO tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
