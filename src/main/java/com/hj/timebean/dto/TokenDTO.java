package com.hj.timebean.dto;

import lombok.Builder;

@Builder
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private long accessTokenExpiresIn;

    public TokenDTO(String grantType, String accessToken, String refreshToken, long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }
}
