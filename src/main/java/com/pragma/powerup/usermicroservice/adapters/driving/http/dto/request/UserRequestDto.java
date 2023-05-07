package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String surname;
    private Long dniNumber;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private String password;
    private Long idRole;
}