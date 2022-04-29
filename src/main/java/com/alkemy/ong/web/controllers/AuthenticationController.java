package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.authentication.Auth;
import com.alkemy.ong.domain.authentication.AuthService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(toAuthResponse(authService.auth(toAuth(username, password)).getEmail()));
    }

    private Auth toAuth(String username, String password) {
        return Auth.builder()
                .email(username)
                .password(password)
                .build();
    }

    private AuthenticationResponse toAuthResponse(String auth) {
        return AuthenticationResponse.builder().username(auth).build();
    }

    @Data
    @Builder
    private static class AuthenticationResponse {
        private String username;
    }

    @Data
    private static class AuthenticationRequest {
        private String username;
        private String password;
    }
}
