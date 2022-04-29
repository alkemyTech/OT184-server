package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.authentication.Auth;
import com.alkemy.ong.domain.authentication.AuthService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(toAuthResponse(authService.auth(toAuth(authRequest)).getEmail()));
    }

    private Auth toAuth(AuthenticationRequest authenticationRequest) {
        return Auth.builder()
                .email(authenticationRequest.getUsername())
                .password(authenticationRequest.getPassword())
                .build();
    }

    private AuthenticationResponse toAuthResponse(String auth) {
        return AuthenticationResponse.builder().jwt(auth).build();
    }

    @Data
    @Builder
    public static class AuthenticationResponse {
        private String jwt;
    }

    @Data
    public static class AuthenticationRequest {
        private String username;
        private String password;
    }
}
