package com.example.project.uber.uberApp.controllers;

import com.example.project.uber.uberApp.advices.ApiResponse;
import com.example.project.uber.uberApp.dto.SignupDto;
import com.example.project.uber.uberApp.dto.UserDto;
import com.example.project.uber.uberApp.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto userDto = authService.signup(signupDto);
        return new ApiResponse<>(userDto);
    }
}
