package com.SpringUygulama.ToDo.controller;

import com.SpringUygulama.ToDo.business.service.UserService;
import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TokenResponse;
import com.SpringUygulama.ToDo.dto.request.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public TokenResponse register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
       return userService.saveUser(userRequestDTO);
    }

    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}