package com.pragma.powerup.usermicroservice.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
    public static final String DNI_NUMBER_FORMAT = "^[1-9]\\d{0,19}$";
    public static final String DATE_TIME_FORMAT ="yyyy-MM-dd";
    public static final Long ADMINISTRATOR_ROLE_ID = 1L;
    public static final Long OWNER_ROLE_ID = 2L;
    public static final Long EMPLOYEE_ROLE_ID = 3L;
    public static final Long CUSTOMER_ROLE_ID = 4L;
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String USER_DELETED_MESSAGE = "User deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials or role not allowed";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String DNI_ALREADY_EXISTS_MESSAGE = "A user already exists with the DNI number provided";
    public static final String DNI_IS_NOT_NUMBER_MESSAGE = "Dni is not a number";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A user with that email already exists";
    public static final String PHONE_ALREADY_EXISTS_MESSAGE = "A user with that phone already exists";
    public static final String PHONE_LENGTH_INVALID_MESSAGE = "The phone number must have a maximum of 13 characters";
    public static final String INVALID_AGE_MESSAGE = "The owner must be of legal age";
    public static final String INVALID_DATE_FORMAT_MESSAGE = "The date must be entered in yyyy-mm-dd format";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String ID_ROLE_INVALID_MESSAGE = "The idRole must be between 1 and 4";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the role provided";
    public static final String USER_NOT_FOUND_MESSAGE = "No user found with the role provided";
    public static final String USER_FOUND_MESSAGE = "User found with the id provided";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
