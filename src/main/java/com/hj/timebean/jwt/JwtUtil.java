package com.hj.timebean.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {

    private String accessSecretKey;
    private String refreshSecretKey;

    @Value("${expiration.access}")
    private long accessExpirationMs;

    @Value("${expiration.refresh}")
    private long refreshExpirationMs;

    public JwtUtil(
            @Value("${spring.jwt.accessSecret}") String accessSecretKey,
            @Value("${spring.jwt.refreshSecret}") String refreshSecretKey) {
        this.accessSecretKey = accessSecretKey;
        this.refreshSecretKey = refreshSecretKey;
    }

    //memberId
    public String getMemberId(String token) {
        return Jwts.parserBuilder().setSigningKey(accessSecretKey).build().parseClaimsJws(token).getBody().get("memberId", String.class);
    }

    public String getRole(String token) {
        return Jwts.parserBuilder().setSigningKey(accessSecretKey).build().parseClaimsJws(token).getBody().get("role", String.class);
    }

    public boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(accessSecretKey).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    //token 생성메서드
    public String accessCreateJwt(String memberId, String role) {

        return Jwts.builder()
                .claim("memberId", memberId)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpirationMs))
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();
    }

    public String refreshCreateJwt(String memberId) {
        return Jwts.builder()
                .claim("memberId", memberId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpirationMs))
                .signWith(SignatureAlgorithm.HS256, refreshSecretKey)
                .compact();

    }
}
