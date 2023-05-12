package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    private String name;

    @NotBlank(message = "Surname is required ")
    @NotNull(message = "Surname is required ")
    private String surname;

    @Pattern(regexp = "[0-9]", message = "The identification number must be numeric")
    @NotNull(message = "Identification number is required")
    private Long dniNumber;

    private LocalDate birthdate;

    @Pattern(regexp = "^\\+?57\\s(3[0-5]|7[1-9])\\d{8}$", message = "Number phone must be a valid number phone")
    @NotBlank(message = "Number phone number is required")
    @NotNull(message = "Number phone number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    //@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$")
    private String password;
    private Role role;
    @Pattern(regexp = "^[1-4]$", message = "Role must be a number between 1 and 4")
    private Long idRole;
}