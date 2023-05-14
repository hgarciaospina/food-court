package com.pragma.powerup.usermicroservice.domain.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class UserValidation {
    public static boolean validateAge(LocalDate birthdate) {
        return ChronoUnit.YEARS.between(birthdate, LocalDate.now()) >= 18;
    }

    public static boolean dateValidFormat(LocalDate birthdate) {
        try {
            birthdate.get(ChronoField.YEAR);
            birthdate.get(ChronoField.MONTH_OF_YEAR);
            birthdate.get(ChronoField.DAY_OF_MONTH);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }

    }
    public static boolean idRolValid(Long idRol) {
        return (idRol >=1 && idRol <=5);
    }
    public static boolean lengthValidPhoneNumber(String phone) { return phone.length() <= 13; }
}