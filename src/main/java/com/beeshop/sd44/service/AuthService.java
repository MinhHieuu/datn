package com.beeshop.sd44.service;

import com.beeshop.sd44.dto.request.LoginRequest;
import com.beeshop.sd44.dto.response.LoginResponse;
import com.beeshop.sd44.dto.response.UserResponse;
import com.beeshop.sd44.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JWTService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    public AuthService(JWTService jwtService, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        User user = userService.getByEmail(loginRequest.getEmail());
        if(user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return null;
        }
        return new LoginResponse(jwtService.createToken(user.getRole(), user.getId().toString()), buildRespone(user));
    }

    public UserResponse buildRespone(User user) {
        UserResponse response = new UserResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setAddress(user.getAddress());
        response.setRole(user.getRole());
        return response;
    }
}
