package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserServicePort {
    boolean isOwnerUser(Long id);
    UserResponseDto saveUser(UserRequestDto user);
}