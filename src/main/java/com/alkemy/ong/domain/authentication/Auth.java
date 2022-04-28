package com.alkemy.ong.domain.authentication;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Auth {
    private String email;
    private String password;
    private String role;
}
