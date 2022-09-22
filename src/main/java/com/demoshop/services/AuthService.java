package com.demoshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.demoshop.auth.JwtTokenUtil;
import com.demoshop.entities.User;
import com.demoshop.requests.AuthRequest;
import com.demoshop.responses.AuthResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public AuthResponse userLogin(AuthRequest request) {
        User user = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            user = (User) authentication.getPrincipal();
        } catch (BadCredentialsException e) {
            log.info(e.getLocalizedMessage() + ": " + request.getEmail());
        }
        if (user == null) {
            throw new BadCredentialsException(request.getEmail());// TODO replace with custom exception
        }
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
        return response;
    }
}
