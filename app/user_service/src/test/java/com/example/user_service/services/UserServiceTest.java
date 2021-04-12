package com.example.user_service.services;

import com.example.user_service.entities.UserEntity;
import com.example.user_service.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository database;

    @Test
    public void registerUser_usernameTooShort() {
        var userToRegister = new UserEntity(2L, "user", "password");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.USERNAME_NOT_LONG_ENOUGH, userService.getErrorCode());
    }

    @Test
    public void registerUser_usernameDoesNotMatchRegex() {
        var userToRegister = new UserEntity(2L, "!!!!!!!!!", "password");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.USERNAME_NOT_MATCH_REGEX, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordNoLowercase() {
        var userToRegister = new UserEntity(2L, "username", "PASSWORD");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_LOWERCASE_LETTER, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordNoUppercase() {
        var userToRegister = new UserEntity(2L, "username", "password");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_UPPERCASE_LETTER, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordNoNumber() {
        var userToRegister = new UserEntity(2L, "username", "Password");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_NUMBER, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordNoSpecialChar() {
        var userToRegister = new UserEntity(2L, "username", "Password1");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_SPECIAL_CHARACTER, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordNotLongEnough() {
        var userToRegister = new UserEntity(2L, "username", "Pas1=");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NOT_LONG_ENOUGH, userService.getErrorCode());
    }

    @Test
    public void registerUser_passwordInvalidChar() {
        var userToRegister = new UserEntity(2L, "username", "[[[[]]]]");
        Assertions.assertFalse(userService.registerUser(userToRegister));
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_INVALID_CHARACTER, userService.getErrorCode());
    }

    @Test
    public void registerUser_userAlreadyExists() {
        var users = List.of(new UserEntity(1L, "username", "Password1="));
        var userToRegister = new UserEntity(2L, "username", "Password1=");

        Mockito.when(database.findAll()).thenReturn(users);

        Assertions.assertFalse(userService.registerUser(userToRegister));
        Mockito.verify(database, Mockito.times(1)).findAll();
    }

    @Test
    public void registerUser_normalBehaviour() {
        var users = List.of(new UserEntity(1L, "username", "Password1="));
        var userToRegister = new UserEntity(2L, "differentUsername", "differentPassword1=");

        Mockito.when(database.findAll()).thenReturn(users);

        Assertions.assertTrue(userService.registerUser(userToRegister));
        Mockito.verify(database, Mockito.times(1)).findAll();
        Mockito.verify(database, Mockito.times(1)).save(userToRegister);
    }

    @Test
    public void getUserById() {
        var expected = Optional.of(new UserEntity(1L, "username", "password"));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var result = userService.getUserById(1L);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expected, result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
    }

    @Test
    public void deleteUser_userDoesNotExist() {
        Optional<UserEntity> expected = Optional.empty();

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var result = userService.deleteUser(1L);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
    }

    @Test
    public void deleteUser_normalBehaviour() {
        var expected = Optional.of(new UserEntity(1L, "username", "password"));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var result = userService.deleteUser(1L);
        Assertions.assertTrue(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Mockito.verify(database, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void updateUser_userDoesNotExist() {
        Optional<UserEntity> expected = Optional.empty();

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "user", "pass");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
    }

    @Test
    public void updateUser_newUsernameTooShort() {
        var expected = Optional.of(new UserEntity(1L, "username", "password"));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "user", "pass");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.USERNAME_NOT_LONG_ENOUGH, userService.getErrorCode());
    }

    @Test
    public void updateUser_newUsernameDoesNotMatchRegex() {
        var expected = Optional.of(new UserEntity(1L, "username", "password"));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "!!!!!!!!!", "pass");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.USERNAME_NOT_MATCH_REGEX, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordNoLowercase() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "PASSWORD");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_LOWERCASE_LETTER, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordNoUppercase() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "password");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_UPPERCASE_LETTER, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordNoNumber() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "Password");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_NUMBER, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordNoSpecialChar() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "Password1");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NO_SPECIAL_CHARACTER, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordTooShort() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "Pas1=");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_NOT_LONG_ENOUGH, userService.getErrorCode());
    }

    @Test
    public void updateUser_newPasswordInvalidChar() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "username", "[[[[]]]]");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertFalse(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(UserService.ErrorCode.PASSWORD_INVALID_CHARACTER, userService.getErrorCode());
    }

    @Test
    public void updateUser_normalBehaviour() {
        var expected = Optional.of(new UserEntity(1L, "username", "Password1="));

        Mockito.when(database.findById(1L)).thenReturn(expected);

        var updatedUser = new UserEntity(1L, "differentUsername", "differentPassword1=");
        var result = userService.updateUser(1L, updatedUser);
        Assertions.assertTrue(result);
        Mockito.verify(database, Mockito.times(1)).findById(1L);
        Mockito.verify(database, Mockito.times(1)).save(updatedUser);
    }
}