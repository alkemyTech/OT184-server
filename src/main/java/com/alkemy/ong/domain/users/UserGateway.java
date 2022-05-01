package com.alkemy.ong.domain.users;

import java.util.List;

public interface UserGateway {
  List<User> findAll();

  void delete(Long id);

  UserDetails loadUserByUsername (String username) throws UsernameNotFoundException;

  Users findByEmail(String email);
}
