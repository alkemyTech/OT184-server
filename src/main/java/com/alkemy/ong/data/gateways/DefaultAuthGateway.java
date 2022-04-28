package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.entities.UserEntity;
import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.authentication.AuthGateway;
import com.alkemy.ong.domain.authentication.Auth;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DefaultAuthGateway implements AuthGateway {

    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    public DefaultAuthGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Auth auth(Auth authUser) throws InvalidCredentialsException {

        UserDetails userDetails;

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUser.getEmail(), authUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        return Auth.builder().email(auth.getName()).build();

    }

}
