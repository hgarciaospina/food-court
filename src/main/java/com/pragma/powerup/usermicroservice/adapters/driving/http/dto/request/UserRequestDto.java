package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import jakarta.validation.constraints.*;
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

    //@Pattern(regexp = "[0-9]", message = "The identification number must be numeric")
    @NotNull(message = "Identification number is required")
    private Long dniNumber;

    @NotNull(message = "birthdate is required")
    @NotEmpty(message = "birthdate is required")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthdate;

    @Pattern(regexp = "^(\\+57)?\\s?(\\d{1})?[\\s|-]?(\\d{3})?[\\s|-]?(\\d{3})[\\s|-]?(\\d{2})[\\s|-]?(\\d{2})$", message = "Number phone must be a valid number phone")
    @NotBlank(message = "Number phone is required")
    @NotNull(message = "Number phone number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message="must be a correctly formatted email address")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$")
    private String password;

    private Role role;

    private Long idRole;
}