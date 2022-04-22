package com.alkemy.ong.domain.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserGateway userGateway;
  
  public List<User> findAll() {
    return userGateway.findAll();
  }
}
