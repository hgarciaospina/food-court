package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    @InjectMocks
    private UserUseCase userUseCase;
    @Test
        void saveUser() {
        Role role = Role.builder()
                .id(1L)
                .name("ADMINISTRATOR_ROLE")
                .description("Administrator role")
                .build();

         User user = User.builder()
                 .id(1L)
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
                .name("Pedro")
                .surname("Picapiedra")
                .dniNumber("100010001")
                .birthdate("1980-10-23")
                .phone("+573234567890")
                .email("pedro@correo.com")
                .role(role)
                .build();

        when(userPersistencePort.saveUser(user)).thenReturn(userResponseDto);
        UserResponseDto createdUser=userUseCase.saveUser(user);
        assertEquals(userResponseDto.getBirthdate(), createdUser.getBirthdate());
        verify(userPersistencePort,times(1)).saveUser(user);
    }
}