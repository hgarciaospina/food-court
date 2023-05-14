package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final IRoleRepository userRole;
    private final IUserRequestMapper userRequestMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        RoleEntity roleEntity = userRole.findById(userRequestDto.getIdRole())
        .orElseThrow(RoleNotFoundException::new);
        userRequestDto.setRole(roleEntityMapper.roleEntityToRole(roleEntity));
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }
}