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
        if (checkUsername(user.getName()) != 30 && checkPassword(user.getPassword()) != 30) {
            return false;
        }

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

    /**
     * Private function that checks whether or not a given username corresponds to the following rules:
     * - must have at least 6 characters
     * - all characters must be either letters (uppercase or lowercase), numbers or special characters from the set {'.', '-', '_'}
     * @param username the username to be checked
     * @return on return, the following codes are possible
     * - 15 : the username does not have the minimum length
     * - 21 : the username does not match the Regex
     * - 30 : the username is OK
     * The codes are in order. First rule to be broken returns the associated code.
     */
    private int checkUsername(String username) {
        if (username.length() < 6)
            return 15;
        if (!username.matches("[a-zA-Z0-9._-]+"))
            return 21;
        return 30;
    }

    /**
     * Private function that checks whether or now a given password is strong enough.
     * For a password to pass the test, it needs to fulfill the following criteria:
     * - must have at least one lowercase letter
     * - must have at least one uppercase letter
     * - must have at least a number
     * - must have at least a special character from the following set {'-', '_', '+', '='}
     * - must be at least 6 characters long
     * @param password the password that needs to be checked
     * @return on return, the following codes are possible
     * - 11 : the password does not contain a lowercase letter
     * - 12 : the password does not contain an uppercase letter
     * - 13 : the password does not contain a number
     * - 14 : the password does not contain a special character
     * - 15 : the password doest not have the minimum length
     * - 30 : the password is OK
     * The codes are in order (the first broken rule returns the code associated with it).
     */
    private int checkPassword(String password) {
        boolean containsLowercase = false;
        boolean containsUppercase = false;
        boolean containsNumber = false;
        boolean specialCharacter = false;
        char currentChar;
        for (int i = 0; i < password.length(); i++) {
            currentChar = password.charAt(i);
            if (currentChar >= 'a' && currentChar <= 'z') {
                containsLowercase = true;
            }
            else if (currentChar >= 'A' && currentChar <= 'Z') {
                containsUppercase = true;
            }
            else if (currentChar >= '0' && currentChar <= '9') {
                containsNumber = true;
            }
            else if (currentChar == '-' || currentChar == '_' || currentChar == '+' || currentChar == '=') {
                specialCharacter = true;
            }
        }
        if (!containsLowercase)
            return 11;
        if (!containsUppercase)
            return 12;
        if (!containsNumber)
            return 13;
        if (!specialCharacter)
            return 14;
        if (password.length() < 6)
            return 15;
        return 30;
    }
}
