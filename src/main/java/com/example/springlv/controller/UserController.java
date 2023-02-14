package com.example.springlv.controller;

import com.example.springlv.dto.LoginResponseDto;
import com.example.springlv.dto.SignupRequestDto;
import com.example.springlv.dto.SignupResponseDto;
import com.example.springlv.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody SignupRequestDto signupRequestDto, HttpServletResponse response){
        return userService.login(signupRequestDto , response);
        }
    }


