package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.*;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    private UserUseCase userUseCase;

    @InjectMocks
    private User user;

    @Autowired
    PasswordEncoder passwordEncode;

    @BeforeEach
    void setUp() {
        openMocks(this);
        passwordEncode = new BCryptPasswordEncoder();
        userUseCase = new UserUseCase(userPersistencePort);

        Role role = new Role(2L, "ROLE_OWNER", "Permissions for the owner");
        user = new User();
        user.setId(1L);
        user.setName("Pedro");
        user.setSurname("Jaramillo");
        user.setDniNumber(100002001L);
        user.setBirthdate("1970-12-24");
        user.setPhone("9 9887234");
        user.setEmail("pedro@gmail.com");
        user.setPassword(passwordEncode.encode("1234"));
        user.setRole(role);
    }

    @Test
        @DisplayName("Expected value equal to actual value")
        void saveUser() {
            userUseCase.saveUser(user);
            verify(userPersistencePort).saveUser(user);
        }
}