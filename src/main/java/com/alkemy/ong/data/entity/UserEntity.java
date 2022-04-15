package com.alkemy.ong.data.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  private String email;

  private String password;

  private String photo;

  @ManyToOne
  private RoleEntity role;
}
