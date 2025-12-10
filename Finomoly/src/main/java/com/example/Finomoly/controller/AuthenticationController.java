package com.example.Finomoly.controller;


import com.example.Finomoly.dto.LoginDto;
import com.example.Finomoly.dto.UserDto;
import com.example.Finomoly.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto){
        return authenticationService.signup(userDto);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return authenticationService.login(loginDto);
    }
}
