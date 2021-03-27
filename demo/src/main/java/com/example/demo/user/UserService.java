package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<CUser> getUsers() {
        return userRepository.findAll();
    }

    public boolean registerUser(CUser user) {
        var maybeUser = userRepository.findAll().stream()
                .filter(u -> u.getName().equals(user.getName()))
                .findFirst();
        if (maybeUser.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Optional<CUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateUser(Long id, CUser user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
