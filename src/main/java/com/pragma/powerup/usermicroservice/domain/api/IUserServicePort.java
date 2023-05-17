package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserServicePort {
    boolean isOwnerUser(Long id);
    void saveUser(User user);
}