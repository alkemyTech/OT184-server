package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> list() {
    List<User> users = userService.list();
    return ResponseEntity
        .ok().body(toListDto(users));
  }

  private List<UserDto> toListDto(List<User> users) {
    return users.stream().map(user ->
            UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build())
        .collect(Collectors.toList());
  }

  @Builder
  @Data
  public static class UserDto {
    Long id;
    String firstName;
    String lastName;
    String email;
    String password;
    String photo;
    Role role;
  }
}
