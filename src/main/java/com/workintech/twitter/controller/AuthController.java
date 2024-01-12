package com.workintech.twitter.controller;

import com.workintech.twitter.dto.RegistrationUser;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.service.AuthenticationService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;
    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationUser registrationUser){
        return authenticationService.register(registrationUser.email(),
                registrationUser.password(),registrationUser.firstName(),
                registrationUser.lastName(),registrationUser.username());
    }
}
