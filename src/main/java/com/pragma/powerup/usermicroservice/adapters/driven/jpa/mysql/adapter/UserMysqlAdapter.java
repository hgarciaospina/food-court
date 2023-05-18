package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.adapters.driving.http.util.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isOwnerUser(Long id) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        return optionalUserEntity.isPresent() && optionalUserEntity.get().getRole().getId().equals(Constants.OWNER_ROLE_ID);
    }

    @Override
    public UserResponseDto saveUser(User user) {
        validations(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityMapper.toUserResponseDto(userRepository.save(userEntityMapper.toEntity(user)));
    }
    private void validations(User user) {
        if (userRepository.findByDniNumber(user.getDniNumber()).isPresent())
            throw new DniAlreadyExistsException();

        if(!UserValidation.validateDni(user.getDniNumber())){
            throw new  DniIsNotNumberException();
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new MailAlreadyExistsException();

        if (userRepository.findByPhone(user.getPhone()).isPresent())
            throw new PhoneAlreadyExistsException();

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
    }
}