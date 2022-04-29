package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.authentication.Auth;
import com.alkemy.ong.domain.authentication.AuthService;
import com.alkemy.ong.domain.users.Users;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private AuthService authService;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@RequestParam("username") String username, @RequestParam("password") String password) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            passwordEncoder.matches(password, userService.loadUserByUsername(username).getPassword());
//
//            SecurityContextHolder
//                    .getContext()
//                    .setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
//        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toAuthResponse(authentication.getName()));
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
