package com.alkemy.ong.domain.authentication;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthGateway authGateway;

    public AuthService(AuthGateway authGateway) { this.authGateway = authGateway;}

    public Auth auth(Auth authentication ) { return authGateway.auth(authentication); }
}
