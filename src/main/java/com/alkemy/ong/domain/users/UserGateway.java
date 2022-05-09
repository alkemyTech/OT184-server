package com.alkemy.ong.domain.users;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserGateway {
    List<Users> findAll();

    void delete(Long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    Users findById(Long id);

    Users findByEmail(String email);

    Users create(Users users);
}
