package org.example.prj1.controller;

import org.example.prj1.dto.request.LoginRequest;
import org.example.prj1.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        return loginService.login(loginRequest);
    }
    @GetMapping("/login")
    public String login() {

        return "login";
    }

}
