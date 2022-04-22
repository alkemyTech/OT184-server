package com.alkemy.ong.domain.users;

import com.alkemy.ong.domain.roles.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    Long id;

    String firstName;

    String lastName;

    String email;

    String password;

    String photo;

    Role role;
}
