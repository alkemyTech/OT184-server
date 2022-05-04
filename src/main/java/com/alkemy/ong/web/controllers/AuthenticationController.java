package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.exceptions.CommunicationException;
import com.alkemy.ong.web.security.CustomUserDetails;
import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.UserService;
import com.alkemy.ong.domain.users.Users;
import com.alkemy.ong.web.security.JwtUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final UserService userService;

  public AuthenticationController(AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

    @PostMapping("/login")
    public ResponseEntity<?> singIn(@RequestParam("username") String username,
                                    @RequestParam("password") String password) {
        CustomUserDetails userDetails;
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(auth);
            userDetails = (CustomUserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new CommunicationException("Incorrect username or password");
        }

        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(toAuthResponse(jwt));
    }

  private AuthenticationResponse toAuthResponse(String auth) {
    return AuthenticationResponse.builder().jwt(auth).build();
  }

  @GetMapping("/me")
  public ResponseEntity<UserController.UserDto> getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = (String) authentication.getPrincipal();
    Users userEntity = userService.findByEmail(email);
    return ResponseEntity.ok(toDto(userEntity));
  }

  private UserController.UserDto toDto(Users user) {
    return UserController.UserDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .photo(user.getPhoto())
        .role(roleToDto(user))
        .build();
  }

  private UserController.RoleDto roleToDto(Users user) {
    Role role = user.getRole();
    return UserController.RoleDto.builder()
        .id(role.getId())
        .name(role.getName())
        .description(role.getDescription())
        .build();
  }

  @Data
  @Builder
  private static class AuthenticationResponse {
        private String jwt;
  }
}
