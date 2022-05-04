package com.alkemy.ong.domain.users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final UserGateway userGateway;
  
  public List<Users> findAll() {
    return userGateway.findAll();
  }

  public void delete(Long id){
    userGateway.delete(id);
  }

  public Users findByEmail(String email) {return userGateway.findByEmail(email);}

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userGateway.loadUserByUsername(username);
  }

  public Users findById(Long id) {
    return userGateway.findById(id);
  }

  public Users findByEmail(String email) {
    return userGateway.findByEmail(email);
  }
}
