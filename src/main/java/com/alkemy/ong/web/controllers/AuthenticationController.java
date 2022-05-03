package com.alkemy.ong.web.controllers;

<<<<<<< HEAD
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
=======
import com.alkemy.ong.domain.exceptions.CommunicationException;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
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
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> fd1bff5 (add auth controller)
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager,
                                    PasswordEncoder passwordEncoder,
                                    UserService userService,
                                    JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity.BodyBuilder signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.save(
                User.builder()
                        .email(signUpRequestDto.getEmail())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .firstName(signUpRequestDto.getFirstName())
                        .lastName(signUpRequestDto.getLastName())
                        .build()
        );
        return ResponseEntity.ok();
    }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestParam("username") String username,
                                    @RequestParam("password") String password) throws Exception {
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
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private AuthenticationResponse toAuthResponse(String auth) {
        return AuthenticationResponse.builder().jwt(auth).build();
    }

    @Data
    private static class SignUpRequestDto {
        private String email;
        private String password;
        private String firstName;
        private String lastName;
    }

    @Data
    @Builder
    private static class AuthenticationResponse {
        private String jwt;
    }
}
