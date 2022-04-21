package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.roles.Role;
import com.alkemy.ong.domain.users.User;
import com.alkemy.ong.domain.users.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultUserGateway implements UserGateway {
  private final UserRepository userRepository;

  @Override
  public List<User> list() {
    return userRepository.findAll()
        .stream()
        .map(userEntity -> User.builder()
            .id(userEntity.getId())
            .firstName(userEntity.getFirstName())
            .lastName(userEntity.getLastName())
            .email(userEntity.getEmail())
            .photo(userEntity.getPhoto())
            .role(
                Role.builder()
                    .id(userEntity.getRole().getId())
                    .description(userEntity.getRole().getDescription())
                    .build()
            )
            .build())
        .collect(Collectors.toList());
  }
}
