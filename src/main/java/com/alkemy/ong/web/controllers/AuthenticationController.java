package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.exceptions.CommunicationException;
import com.alkemy.ong.web.config.SecurityConfiguration.JwtUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(auth);
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new CommunicationException("Incorrect username or password");
        }

        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(toAuthResponse(jwt));
    }

    private AuthenticationResponse toAuthResponse(String auth) {
        return AuthenticationResponse.builder().jwt(auth).build();
    }

    @Data
    @Builder
    private static class AuthenticationResponse {
        private String jwt;
    }
}
