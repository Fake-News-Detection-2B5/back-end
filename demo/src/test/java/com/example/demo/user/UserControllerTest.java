package com.example.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void getUsers() throws Exception {
        List<CUser> users = new ArrayList<>();
        var user1 = new CUser(1L, "username1", "password1");
        users.add(user1);

        Mockito.when(userService.getUsers()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/users").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Mockito.verify(userService, Mockito.times(1)).getUsers();

        String expected = "[{\"id\":1,\"name\":\"username1\",\"password\":\"password1\"}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    void getUserById() throws Exception {
        Optional<CUser> user1 = Optional.of(new CUser(1L, "username1", "password1"));

        Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(user1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/v1/users/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Mockito.verify(userService, Mockito.times(1)).getUserById(1L);

        String expected = "{\"id\":1,\"name\":\"username1\",\"password\":\"password1\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    void registerUser() throws Exception {
        var user1 = new CUser(1L, "username1", "password1");
        String user1JSON = "{\"id\":1,\"name\":\"username1\",\"password\":\"password1\"}";

        Mockito.when(userService.registerUser(Mockito.any(CUser.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/v1/users").content(user1JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder);
        Mockito.verify(userService, Mockito.times(1)).registerUser(user1);
    }

    @Test
    void deleteUser() throws Exception {
        Mockito.when(userService.deleteUser(Mockito.anyLong())).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/users/1");

        mockMvc.perform(requestBuilder);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(1L);
    }

    @Test
    void updateUser() throws Exception {
        var user1 = new CUser(1L, "username1", "password1");
        String user1JSON = "{\"id\":1,\"name\":\"username1\",\"password\":\"password1\"}";

        Mockito.when(userService.updateUser(Mockito.anyLong(), Mockito.any(CUser.class))).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
                "/api/v1/users/1").content(user1JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder);
        Mockito.verify(userService, Mockito.times(1)).updateUser(1L, user1);
    }
}