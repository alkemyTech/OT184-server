package com.alkemy.ong.domain.authentication;

import org.apache.http.auth.InvalidCredentialsException;

public interface AuthGateway {

    Auth auth(Auth authentication) throws InvalidCredentialsException;
}
