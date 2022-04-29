package com.alkemy.ong.web.controllers;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toAuthResponse(auth.getName()));
    }

   private AuthenticationResponse toAuthResponse(String auth) {
        return AuthenticationResponse.builder().username(auth).build();
    }

    @Data
    @Builder
    private static class AuthenticationResponse {
        private String username;
    }
}
