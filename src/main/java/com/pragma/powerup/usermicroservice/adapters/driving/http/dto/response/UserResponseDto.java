package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private String name;
    private String surname;
    private String dniNumber;
    private LocalDate birthdate;
    private String phone;
    private String email;
    private Role role;
}