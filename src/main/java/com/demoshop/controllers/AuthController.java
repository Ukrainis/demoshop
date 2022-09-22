package com.demoshop.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoshop.requests.AuthRequest;
import com.demoshop.responses.AuthResponse;
import com.demoshop.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid AuthRequest authRequest) {
        AuthResponse response = authService.userLogin(authRequest);
        return ResponseEntity.ok().body(response);
    }
}
