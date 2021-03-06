package com.example.user_service.services;

import com.example.user_service.entities.UserEntity;
import com.example.user_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public enum ErrorCode {
        NOT_INIT,
        USERNAME_NOT_LONG_ENOUGH, USERNAME_NOT_MATCH_REGEX,
        PASSWORD_NO_LOWERCASE_LETTER, PASSWORD_NO_UPPERCASE_LETTER, PASSWORD_NO_NUMBER, PASSWORD_NO_SPECIAL_CHARACTER, PASSWORD_INVALID_CHARACTER, PASSWORD_NOT_LONG_ENOUGH,
        NO_ERRORS
    }
    private ErrorCode errorCode;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean registerUser(UserEntity user) {
        if (checkUserCredentials(user)) {
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

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean deleteAllUsers() {
        userRepository.deleteAll();
        return true;
    }

    @Transactional
    public boolean updateUser(Long id, UserEntity user) {
        if (userRepository.findById(id).isPresent()) {

            if (checkUserCredentials(user)) {
                return false;
            }

            userRepository.update(user.getName(), user.getPassword(), id);
            return true;
        }

        return false;
    }

    private boolean checkUserCredentials(UserEntity user) {
        checkUsername(user.getName());
        if (getErrorCode() != ErrorCode.NO_ERRORS) {
            return true;
        }

        checkPassword(user.getPassword());
        return getErrorCode() != ErrorCode.NO_ERRORS;
    }

    /**
     * Private function that checks whether or not a given username corresponds to the following rules:
     * - must have at least 6 characters
     * - all characters must be either letters (uppercase or lowercase), numbers or special characters from the set {'.', '-', '_'}
     * When a rule is broken, the error code variable will be set. The rules are in order (first rule to not be respected sets the code).
     * @param username the username to be checked
     */
    private void checkUsername(String username) {
        if (username.length() < 6) {
            setErrorCode(ErrorCode.USERNAME_NOT_LONG_ENOUGH);
            return;
        }
        if (!username.matches("[a-zA-Z0-9._-]+")) {
            setErrorCode(ErrorCode.USERNAME_NOT_MATCH_REGEX);
            return;
        }
        setErrorCode(ErrorCode.NO_ERRORS);
    }

    /**
     * Private function that checks whether or now a given password is strong enough.
     * For a password to pass the test, it needs to fulfill the following criteria:
     * - must have at least one lowercase letter
     * - must have at least one uppercase letter
     * - must have at least a number
     * - must have at least a special character from the following set {'-', '_', '+', '='}
     * - must be at least 6 characters long
     * When a rule is broken, the error code variable will be set. The rules are in order (first rule to not be respected sets the code).
     * @param password the password that needs to be checked
     */
    private void checkPassword(String password) {
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
            else {
                setErrorCode(ErrorCode.PASSWORD_INVALID_CHARACTER);
                return;
            }
        }
        if (!containsLowercase) {
            setErrorCode(ErrorCode.PASSWORD_NO_LOWERCASE_LETTER);
            return;
        }
        if (!containsUppercase) {
            setErrorCode(ErrorCode.PASSWORD_NO_UPPERCASE_LETTER);
            return;
        }
        if (!containsNumber) {
            setErrorCode(ErrorCode.PASSWORD_NO_NUMBER);
            return;
        }
        if (!specialCharacter) {
            setErrorCode(ErrorCode.PASSWORD_NO_SPECIAL_CHARACTER);
            return;
        }
        if (password.length() < 6) {
            setErrorCode(ErrorCode.PASSWORD_NOT_LONG_ENOUGH);
            return;
        }
        setErrorCode(ErrorCode.NO_ERRORS);
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private void setErrorCode(ErrorCode code) {
        this.errorCode = code;
    }
}
