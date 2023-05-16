package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserHandler {
    User findUserById(Long id);
    void saveUser(UserRequestDto personRequestDto);
}