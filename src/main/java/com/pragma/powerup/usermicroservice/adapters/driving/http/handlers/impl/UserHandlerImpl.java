package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort personServicePort;
    private final IRoleRepository personRole;
    private final IUserRequestMapper personRequestMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        RoleEntity roleEntity = personRole.findById(userRequestDto.getIdRole()).orElse(null);
        userRequestDto.setRole(roleEntityMapper.roleEntityToRole(roleEntity));
        personServicePort.saveUser(personRequestMapper.toUser(userRequestDto));
    }
    public void encryptPasswordUserRequestDto (UserRequestDto userRequestDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = userRequestDto.getPassword();
        userRequestDto.setPassword(passwordEncoder.encode(password));
    }
}