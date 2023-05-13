package com.pragma.powerup.usermicroservice.domain.validations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserValidation {
    public static boolean validateAge(LocalDate birthdate) {
        return ChronoUnit.YEARS.between(birthdate, LocalDate.now()) >= 18;
    }
}