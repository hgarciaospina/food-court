package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Name is required")
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 100, message="The name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Surname is required ")
    @NotNull(message = "Surname is required ")
    @Size(min = 2, max = 100, message="The surname must be between 2 and 100 characters")
    private String surname;

    //@Pattern(regexp = "[0-9]", message = "The identification number must be numeric")
    @NotNull(message = "Identification number is required")
    private Long dniNumber;

    @NotBlank(message = "birthdate is required")
    @NotNull(message = "birthdate is required")
    private String birthdate;

    @Pattern(regexp = "^(\\+57)?\\s?(\\d{1})?[\\s|-]?(\\d{3})?[\\s|-]?(\\d{3})[\\s|-]?(\\d{2})[\\s|-]?(\\d{2})$", message = "Number phone must be a valid number phone")
    @NotBlank(message = "Number phone is required")
    @NotNull(message = "Number phone number is required")
    private String phone;

    @NotBlank(message = "Email is required")
    @NotNull(message = "Email is required")
    @Size(min = 6, max = 100, message = "The email must be between 6 and 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message="must be a correctly formatted email address")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$")
    @Size(min = 8, max = 15, message = "\n" +
            "The password must be between 8 and 15 characters, have uppercase, lowercase, numbers and a special character.")
    private String password;

    private Role role;

    private Long idRole;
}