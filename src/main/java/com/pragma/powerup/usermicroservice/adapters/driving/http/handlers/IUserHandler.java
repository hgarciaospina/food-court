package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;

public interface IUserHandler {
    boolean isOwnerUser(Long id);
    void saveUser(UserRequestDto userRequestDto);
}