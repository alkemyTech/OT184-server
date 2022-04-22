package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.RoleEntity;
import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class DefaultUserGateway implements UserGateway {
  private final UserRepository userRepository;

  @Override
  public List<User> findAll() {
    return userRepository.findAll()
        .stream()
        .map(this::toModel)
        .collect(toList());
  }

  private User toModel(UserEntity userEntity) {
    return User.builder()
        .id(userEntity.getId())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .photo(userEntity.getPhoto())
        .role(roleToModel(userEntity))
        .build();
  }

  private Role roleToModel(UserEntity userEntity) {
    RoleEntity role = userEntity.getRole();
    return Role.builder()
        .id(role.getId())
        .name(role.getName())
        .description(role.getDescription())
        .build();
  }
}
