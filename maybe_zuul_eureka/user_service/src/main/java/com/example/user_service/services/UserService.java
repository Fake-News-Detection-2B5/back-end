package com.example.user_service.services;

import com.example.user_service.dtos.UserEntityDTO;
import com.example.user_service.entities.UserEntity;
import com.example.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class that communicates and makes changes to the database.
 */
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // to be implemented
    public UserEntityDTO getByName(String name) {
        return null;
    }

    // to be implemented
    public UserEntityDTO getById(Long id) {
        return null;
    }

    public void register(UserEntityDTO userEntityDTO) {
        userRepository.save(new UserEntity(userEntityDTO));
    }
}
