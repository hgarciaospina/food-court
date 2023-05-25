package com.pragma.powerup.usermicroservice.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String surname;
    private String dniNumber;
    private String birthdate;
    private String phone;
    private String email;
    private String password;
    private Role role;
}