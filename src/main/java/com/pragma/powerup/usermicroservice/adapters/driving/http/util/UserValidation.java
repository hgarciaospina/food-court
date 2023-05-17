package com.pragma.powerup.usermicroservice.adapters.driving.http.util;

import com.pragma.powerup.usermicroservice.configuration.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
public class UserValidation {
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(
            Constants.DATE_TIME_FORMAT);

    public static boolean validateAge(String birthdate) {
        LocalDate validateBirthdate = LocalDate.parse(birthdate);
        return ChronoUnit.YEARS.between(validateBirthdate, LocalDate.now()) >= 18;
    }

    public static boolean dateValidFormat(String birthdate) {
        try {
            DATETIME_FORMATTER.parse(birthdate);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
    public static boolean idRolValid(Long idRol) {
        return (idRol >=1 && idRol <=5);
    }
    public static boolean lengthValidPhoneNumber(String phone) { return phone.length() <= 13; }
}