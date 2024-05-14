package com.hj.timebean.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.lettuce.core.dynamic.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey accessSecretKey;
    private final SecretKey refreshSecretKey;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    public JwtTokenProvider(
            @Value("${jwt.access.secret}")SecretKey accessSecretKey,
            @Value("${jwt.refresh.secret}") SecretKey refreshSecretKey,
            @Value("${jwt.access.expiration")long accessTokenValidityInMilliseconds,
            @Value("${jwt.refresh.expiration")long refreshTokenValidityInMilliseconds) {
        this.accessSecretKey = accessSecretKey;
        this.refreshSecretKey = refreshSecretKey;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }
    public SecretKey getAccessSecretKey() {
        return accessSecretKey;
    }

    public SecretKey getRefreshSecretKey() {
        return refreshSecretKey;
    }

    public long getAccessTokenValidityInMilliseconds() {
        return accessTokenValidityInMilliseconds;
    }

    public long getRefreshTokenValidityInMilliseconds() {
        return refreshTokenValidityInMilliseconds;
    }

    //access token
    public String createAccessToken(String Id){
       // token 발급 현재 시간
       Date now = new Date();
        //token 만료 시간 (현재 시간 + accessTokenValidityInMilliseconds )
        Date validity = new Date(now.getTime()+accessTokenValidityInMilliseconds);

        return Jwts.builder().
                // token 클레임 설정
                setSubject(Id).setIssuedAt(now).setExpiration(validity).
                //서명
                signWith(accessSecretKey, SignatureAlgorithm.HS256).
                //문자열 토큰 반환
                compact();
    }
    //refresh token
    public String createRefreshToken(String Id){
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliseconds);

        return Jwts.builder().setSubject(Id).setIssuedAt(now).setExpiration(validity).signWith(refreshSecretKey,SignatureAlgorithm.ES256).compact();
    }
}
