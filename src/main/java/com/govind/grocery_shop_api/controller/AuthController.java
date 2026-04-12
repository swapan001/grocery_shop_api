package com.govind.grocery_shop_api.controller;

import com.govind.grocery_shop_api.dto.AuthRequest;
import com.govind.grocery_shop_api.dto.AuthResponse;
import com.govind.grocery_shop_api.entity.User;
import com.govind.grocery_shop_api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.register(user);
        return "User Registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        return new AuthResponse(token);
    }
}