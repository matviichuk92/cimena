package com.cinema.controller;

import com.cinema.model.dto.UserRegisterDto;
import com.cinema.security.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public void register(@RequestBody UserRegisterDto userRegisterDto) {
        authenticationService.register(userRegisterDto.getEmail(), userRegisterDto.getPassword());
    }
}
