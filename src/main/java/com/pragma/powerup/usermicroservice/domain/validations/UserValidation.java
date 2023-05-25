package com.pragma.powerup.usermicroservice.domain.validations;
import com.pragma.powerup.usermicroservice.configuration.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.pragma.powerup.usermicroservice.configuration.Constants.DNI_NUMBER_FORMAT;

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
    public static boolean validateDni(String dni) {
        try {
            Pattern pattern = Pattern.compile((DNI_NUMBER_FORMAT));
            Matcher matcher = pattern.matcher(dni);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean idRolValid(Long idRol) {
        return (idRol >=1 && idRol <=5);
    }
    public static boolean lengthValidPhoneNumber(String phone) {
        return ((phone.length() >=7) && (phone.length() <=13)); }
}