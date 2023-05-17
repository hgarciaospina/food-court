package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    //User findUserById(Long id);
    UserResponseDto findUserById(Long id);
    void saveUser(User user);
}