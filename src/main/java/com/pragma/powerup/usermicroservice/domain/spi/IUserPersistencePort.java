package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {
    boolean isOwnerUser(Long id);
    UserResponseDto saveUser(User user);
}