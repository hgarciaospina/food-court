package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @Mock
    private IRolePersistencePort rolePersistencePort;
    @InjectMocks
    private RoleUseCase roleUseCase;

    public static List<Role >getAllRoles() {
        Role role1 = Role.builder()
                .id(1L)
                .name("ADMINISTRATOR_ROLE")
                .description("Administrator role")
                .build();
        Role role2 = Role.builder()
                .id(1L)
                .name("OWNER_ROLE")
                .description("Owner role")
                .build();

        return Arrays.asList(role1, role2);
    }

    @Test
    void testGetAllRoles() {
        List<Role> roles = getAllRoles();

        when(rolePersistencePort.getAllRoles()).thenReturn(getAllRoles());

        List<Role> rolesDb = roleUseCase.getAllRoles();

        assertEquals(rolesDb.size(), roles.size());
        assertEquals(roles.get(0).getId(), rolesDb.get(0).getId());
        assertEquals(roles.get(0).getName(), rolesDb.get(0).getName());
        assertEquals(roles.get(0).getDescription(), rolesDb.get(0).getDescription());
    }
}