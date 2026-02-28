package com.beeshop.sd44.dto.response;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse userResponse;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public LoginResponse(String accessToken, String refreshToken, UserResponse userResponse) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userResponse = userResponse;
    }
}
