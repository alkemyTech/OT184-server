package com.alkemy.ong.domain.authentication;


import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthGateway authGateway;

    public AuthService(AuthGateway authGateway) { this.authGateway = authGateway;}

    public Auth auth(Auth authentication ) throws InvalidCredentialsException { return authGateway.auth(authentication); }
}
