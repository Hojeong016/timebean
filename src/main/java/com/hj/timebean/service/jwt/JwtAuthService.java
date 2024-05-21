package com.hj.timebean.service.jwt;

import com.hj.timebean.dto.SignInDTO;
import com.hj.timebean.dto.SignUpDTO;
import com.hj.timebean.dto.TokenDTO;
import com.hj.timebean.entity.Member;
import com.hj.timebean.entity.RefreshToken;
import com.hj.timebean.jwt.JwtUtil;
import com.hj.timebean.repository.MemberRepository;
import com.hj.timebean.repository.RefreshTokenRepository;
import com.hj.timebean.service.member.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    public JwtAuthService(AuthenticationManager authenticationManager, MemberRepository memberRepository, JwtUtil jwtUtil, RefreshTokenRepository refreshTokenRepository, MemberService memberService, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.memberRepository = memberRepository;
        this.jwtUtil = jwtUtil;
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public SignUpDTO signup(SignUpDTO signUpDTO) {
        // 이미 가입된 유저인지 확인
        if (memberRepository.existsByMemberId(signUpDTO.getMemberId())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        signUpDTO.setPassword(encryptedPassword);

        // 새로운 멤버 생성
        Member member = memberService.signUp(signUpDTO);
        memberRepository.save(member);

        return signUpDTO;
    }

    @Transactional
    public TokenDTO login(SignInDTO signInDTO) {
        // Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInDTO.getMemberId(), signInDTO.getPassword());

        // 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 인증 정보를 SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // JWT 토큰 생성
        TokenDTO tokenDto = jwtUtil.generateToken(authentication);

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .memberMemberId(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDTO reissue(TokenDTO tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!jwtUtil.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = jwtUtil.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByMemberMemberId(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDTO tokenDto = jwtUtil.generateToken(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
