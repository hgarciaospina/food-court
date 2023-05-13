package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.DniAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.InvalidAgeException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PhoneAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
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

        if(!UserValidation.validateAge(user.getBirthdate())){
            throw new InvalidAgeException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        personRepository.save(personEntityMapper.toEntity(user));
    }
}