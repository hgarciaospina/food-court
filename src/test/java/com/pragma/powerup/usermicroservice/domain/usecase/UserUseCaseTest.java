package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.exceptions.InvalidAgeException;
import com.pragma.powerup.usermicroservice.domain.exceptions.InvalidDateFormatException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    PasswordEncoder passwordEncode;
    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @InjectMocks
    private UserUseCase userUseCase;

    @Test
        void testSaveUser() {
        Role role = Role.builder()
                .id(1L)
                .name("ADMINISTRATOR_ROLE")
                .description("Administrator role")
                .build();

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("Pedro")
                .surname("Picapiedra")
                .dniNumber("100010001")
                .birthdate("1980-10-23")
                .phone("+573234567890")
                .email("pedro@correo.com")
                .password(passwordEncode.encode("Leandro2009*"))
                .role(role)
                .build();

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .build();

        when(userPersistencePort.saveUser(any())).thenReturn(userResponseDto);
        UserResponseDto createdUser=userUseCase.saveUser(userRequestDto);
        assertEquals(userResponseDto.getBirthdate(), createdUser.getBirthdate());
        verify(userPersistencePort,times(1)).saveUser(any());
    }

    @Test
    void testSaveUser_InvalidDateFormat(){
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .phone("+573215678989")
                .birthdate("123")
                .build();
        assertThrows(InvalidDateFormatException.class, ()-> userUseCase.saveUser(userRequestDto));
        verifyNoInteractions(userPersistencePort);
        verifyNoInteractions(userEntityMapper);
    }

    @Test
    void testSaveUser_InvalidAge() {
        Role role = Role.builder()
                .id(2L)
                .name("OWNER_ROLE")
                .description("Owner role")
                .build();

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .phone("+573215678989")
                .birthdate("2010-01-01")
                .role(role)
                .build();

        try (MockedStatic<UserValidation> util = Mockito.mockStatic(UserValidation.class)) {
            util.when(() -> UserValidation.lengthValidPhoneNumber(userRequestDto.getPhone())).thenReturn(true);
            util.when(() -> UserValidation.dateValidFormat(userRequestDto.getBirthdate())).thenReturn(true);
            util.when(() -> UserValidation.idRolValid(userRequestDto.getRole().getId())).thenReturn(true);
            util.when(() -> UserValidation.validateDni(userRequestDto.getDniNumber())).thenReturn(true);
            util.when(() -> UserValidation.validateAge(userRequestDto.getBirthdate())).thenReturn(false);

            assertThrows(InvalidAgeException.class, () -> userUseCase.saveUser(userRequestDto));
        }

        verifyNoInteractions(userPersistencePort);
        verifyNoInteractions(userEntityMapper);
    }

}