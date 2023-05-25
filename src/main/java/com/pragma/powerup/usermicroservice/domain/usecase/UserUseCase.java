package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.*;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValidation;
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
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        validations(userRequestDto);
        return userPersistencePort.saveUser(userEntityMapper.toDomain(userRequestDto));
    }

    private void validations(UserRequestDto userRequestDto) {

        if (!UserValidation.lengthValidPhoneNumber(userRequestDto.getPhone()))
            throw new PhoneLengthInvalidException();

        if(!UserValidation.dateValidFormat(userRequestDto.getBirthdate())){
            throw new InvalidDateFormatException();
        }

        if (!UserValidation.idRolValid(userRequestDto.getRole().getId()))
            throw new IdRolInvalidException();

        if (userRequestDto.getRole().getId().equals(Constants.OWNER_ROLE_ID) &&
                (!UserValidation.validateAge(userRequestDto.getBirthdate()))) {
            throw new InvalidAgeException();
        }

        if (!UserValidation.validateDni(userRequestDto.getDniNumber())) {
            throw new DniIsNotNumberException();
        }
    }
}