package com.example.TodoManagement.controller;

import com.example.TodoManagement.dto.JwtAuthResponse;
import com.example.TodoManagement.dto.LoginDto;
import com.example.TodoManagement.dto.RegisterDto;
import com.example.TodoManagement.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    // Build Register REST API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
       String token =  authService.login(loginDto);
       JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
       jwtAuthResponse.setAccessToken(token);
       return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
