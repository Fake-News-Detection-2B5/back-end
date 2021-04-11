package com.example.user_service.services;

import com.example.user_service.dtos.UserEntityDTO;
import com.example.user_service.entities.UserEntity;
import com.example.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<UserEntityDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserEntityDTO(u.getId(), u.getName(), u.getPassword()))
                .collect(Collectors.toList());
    }

    public Optional<UserEntityDTO> getUserById(Long id) {
        return userRepository.findById(id).map(u -> new UserEntityDTO(u.getId(), u.getName(), u.getPassword()));
    }

    public boolean registerUser(UserEntityDTO user) {
        try {
            userRepository.save(new UserEntity(user));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteAllUsers() {
        userRepository.deleteAll();
        return true;
    }
}
