package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.Users;
import com.alkemy.ong.domain.users.UserService;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> findAll() {
    return ResponseEntity.ok().body(toListDto(userService.findAll()));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    userService.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  private List<UserDto> toListDto(List<Users> users) {
    return users.stream()
        .map(this::toDto)
        .collect(toList());
  }

  private UserDto toDto(Users user) {
    return UserDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .photo(user.getPhoto())
        .role(roleToDto(user))
        .build();
  }

  private RoleDto roleToDto(Users user) {
    Role role = user.getRole();
    return RoleDto.builder()
        .id(role.getId())
        .name(role.getName())
        .description(role.getDescription())
        .build();
  }

  @Builder
  @Data
  public static class RoleDto {
    private Long id;
    private String name;
    private String description;
  }

  @Builder
  @Data
  public static class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
    private RoleDto role;
  }
}
