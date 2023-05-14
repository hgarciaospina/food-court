package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository personRepository;
    private final IUserEntityMapper personEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        if (personRepository.findByDniNumber(user.getDniNumber()).isPresent())
            throw new DniAlreadyExistsException();

        if (personRepository.findByEmail(user.getEmail()).isPresent())
            throw new MailAlreadyExistsException();

        if (personRepository.findByPhone(user.getPhone()).isPresent())
            throw new PhoneAlreadyExistsException();
/*
        if(!UserValidation.dateValidFormat(user.getBirthdate())){
            throw new InvalidDateFormatException();
        }
*/
        if (!UserValidation.idRolValid(user.getRole().getId()))
            throw new IdRolInvalidException();

        if (user.getRole().getId().equals(Constants.OWNER_ROLE_ID) &&
                (!UserValidation.validateAge(user.getBirthdate()))) {
            throw new InvalidAgeException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        personRepository.save(personEntityMapper.toEntity(user));
    }
}