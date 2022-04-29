package com.alkemy.ong.data.gateways;

import com.alkemy.ong.data.repositories.UserRepository;
import com.alkemy.ong.domain.authentication.AuthGateway;
import com.alkemy.ong.domain.authentication.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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
    public Auth auth(Auth authUser)  {
        return Auth
                .builder()
                .email(userRepository
                        .findByEmail(authUser.getEmail())
                        .getEmail())
                .build();
    }

}
