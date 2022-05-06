package com.alkemy.ong.web.controllers;

import com.alkemy.ong.domain.email.EmailService;
import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.UserService;
import com.alkemy.ong.domain.users.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder encoder;
    private final String texto = "Gracias por registrarte con nosotros ";

    public UserController(UserService userService, EmailService emailService, PasswordEncoder encoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.encoder = encoder;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok().body(toListDto(userService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id, Authentication authentication) {
        if (hasRole(authentication, id.toString()) || hasRole(authentication, "admin")) {
            return ResponseEntity.ok(toDto(userService.findById(id)));
        }
        return ResponseEntity.ok(toDto(userService.findByEmail((String) authentication.getPrincipal())));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> register(@RequestBody UserBasicDto userBasicDto) {
        Users users = new Users();
        users.setRole(new Role(2L, "user", "asd"));
        users.setFirstName(userBasicDto.getFirstName());
        users.setLastName(userBasicDto.getLastName());
        users.setEmail(userBasicDto.getEmail());
        users.setPassword(encoder.encode(userBasicDto.getPassword()));
        users.setPhoto("asd");
        emailService.sendMail(userBasicDto.getEmail(), "Bienvenido a Alkemy " + userBasicDto.getFirstName(), texto + userBasicDto.getFirstName() +  "" + userBasicDto.getLastName(), "Bienvenido " + userBasicDto.getFirstName());
        Users usuario = userService.save(users);
        return new ResponseEntity<>(toDto(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private List<UserDto> toListDto(List<Users> users) {
        return users.stream()
                .map(this::toDto)
                .collect(toList());
    }

    private boolean hasRole(Authentication authentication, String s) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority(s));
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

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserBasicDto {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
    }
}
