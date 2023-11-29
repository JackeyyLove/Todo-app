package com.example.TodoManagement.service;

import com.example.TodoManagement.dto.LoginDto;
import com.example.TodoManagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
