package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override

    public boolean isOwnerUser(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> userEntity.getRole().getId().equals(Constants.OWNER_ROLE_ID))
                .orElse((false));
    }

    @Override
    public UserResponseDto saveUser(User user) {

        validations(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityMapper.toUserResponseDto(userRepository.save(userEntityMapper.toEntity(user)));

    }
    private void validations(User user){
        if (userRepository.findByDniNumber(user.getDniNumber()).isPresent())
            throw new DniAlreadyExistsException();

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new MailAlreadyExistsException();

        if (userRepository.findByPhone(user.getPhone()).isPresent())
            throw new PhoneAlreadyExistsException();
    }
}