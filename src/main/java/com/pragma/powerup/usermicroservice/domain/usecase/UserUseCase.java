package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.util.UserValidation;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;
    private final IUserEntityMapper userEntityMapper;


    @Override
    public boolean isOwnerUser(Long id) {
        return userPersistencePort.isOwnerUser(id);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto user) {
        validations(user);
        return userPersistencePort.saveUser(userEntityMapper.toDomain(user));
    }

    private void validations(UserRequestDto user) {

        if (!UserValidation.lengthValidPhoneNumber(user.getPhone()))
            throw new PhoneLengthInvalidException();

        if(!UserValidation.dateValidFormat(user.getBirthdate())){
            throw new InvalidDateFormatException();
        }
        if (!UserValidation.idRolValid(user.getRole().getId()))
            throw new IdRolInvalidException();

        if (user.getRole().getId().equals(Constants.OWNER_ROLE_ID) &&
                (!UserValidation.validateAge(user.getBirthdate()))) {
            throw new InvalidAgeException();
        }

        if (!UserValidation.validateDni(user.getDniNumber())) {
            throw new DniIsNotNumberException();
        }
    }
}